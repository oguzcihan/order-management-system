package org.cihan.ordermanagementsystem.order.service;

import lombok.RequiredArgsConstructor;
import org.cihan.ordermanagementsystem.common.exception.BusinessException;
import org.cihan.ordermanagementsystem.common.exception.NotFoundException;
import org.cihan.ordermanagementsystem.common.utils.Constants;
import org.cihan.ordermanagementsystem.customer.service.CustomerService;
import org.cihan.ordermanagementsystem.order.domain.*;
import org.cihan.ordermanagementsystem.order.mapper.OrderItemMapper;
import org.cihan.ordermanagementsystem.order.mapper.OrderMapper;
import org.cihan.ordermanagementsystem.order.repository.OrderRepository;
import org.cihan.ordermanagementsystem.product.domain.Product;
import org.cihan.ordermanagementsystem.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    /**
     * @Transactional anotasyonu, createOrder metodunun bir işlem (transaction)
     * olarak çalışmasını sağlar. Bu, metodun başlangıcından sonuna kadar yapılan işlemlerin
     * bir bütün olarak ele alınmasını sağlar.
     * Eğer işlem sırasında herhangi bir hata (exception)
     * meydana gelirse, yapılan tüm değişiklikler geri alınır (rollback). Bu,
     * veritabanı tutarlılığının korunmasını sağlar.
     * @param orderRequest OrderRequest
     * @return OrderResponse
     */
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        log.info("createOrder operation started");
        validateCustomer(orderRequest.customerId());
        validateOrderItems(orderRequest.orderItems());

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : orderRequest.orderItems()) {
            Product product = validateAndFetchProduct(orderItemRequest);
            updateProductStock(product, orderItemRequest.quantity());

            OrderItem orderItem = buildOrderItem(orderItemRequest, product);
            orderItems.add(orderItem);
            totalAmount = totalAmount.add(orderItem.getTotalPrice());
        }

        Order order = buildOrder(orderRequest.customerId(), orderItems, totalAmount);
        orderItems.forEach(item -> item.setOrder(order));

        // Map saved order and return response
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getOrdersByCustomerId(UUID customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(orderMapper::toOrderResponse
        ).collect(Collectors.toList());
    }

    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage.map(orderMapper::toOrderResponse);
    }

    @Transactional
    public void deleteOrder(UUID orderId) {
        log.info("deleteOrder operation started");
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.ORDER_NOT_FOUND, orderId)));

        // old stock quantity update
        existingOrder.getOrderItems().forEach(item -> {
            Product product = productService.findById(item.getProductId())
                    .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, item.getProductId())));
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            productService.update(product);
        });

        orderRepository.delete(existingOrder);
    }

    public List<OrderResponse> findByFilters(OrderFilter filter) {
        return orderRepository.findByFilters(filter)
                .stream().map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }


    //region Helper Methods

    /**
     * Checks if the customer exists and throws an exception if not.
     * @param customerId UUID
     */
    private void validateCustomer(UUID customerId) {
        customerService.getCustomerById(customerId)
                .orElseThrow(() -> new BusinessException(String.format(Constants.ORDER_NOT_FOUND, customerId)));
    }

    /**
     * Checks if the order items are valid and throws an exception if not.
     * @param orderItemRequests OrderItemRequest
     */
    private void validateOrderItems(List<OrderItemRequest> orderItemRequests) {
        Set<UUID> uniqueProductIds = new HashSet<>();
        orderItemRequests.forEach(item -> {
            if (!uniqueProductIds.add(item.productId())) {
                throw new BusinessException(String.format(Constants.ORDER_REQUEST_DUPLICATE_ORDER_ITEM, item.productId()));
            }
        });
    }

    /**
     * Checks if the product exists and throws an exception if not.
     * @param orderItemRequest OrderItemRequest
     * @return Product
     */
    private Product validateAndFetchProduct(OrderItemRequest orderItemRequest) {
        return productService.findById(orderItemRequest.productId())
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, orderItemRequest.productId())));
    }

    /**
     * Updates the stock quantity of the product
     *
     * @param product  Product
     * @param quantity int
     */
    private void updateProductStock(Product product, int quantity) {
        if (product.getStockQuantity() < quantity) {
            throw new BusinessException(String.format(Constants.INSUFFICIENT_STOCK, product.getId()));
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productService.update(product);
    }

    /**
     * Creates an order item
     * @param orderItemRequest OrderItemRequest
     * @param product          Product
     * @return OrderItem
     */
    private OrderItem buildOrderItem(OrderItemRequest orderItemRequest, Product product) {
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.quantity()));
        return OrderItem.builder()
                .productId(orderItemRequest.productId())
                .quantity(orderItemRequest.quantity())
                .price(product.getPrice())
                .totalPrice(totalPrice)
                .build();
    }

    /**
     * Creates and saves an order
     * @param customerId UUID
     * @param orderItems List<OrderItem>
     * @param totalAmount BigDecimal
     * @return Order
     */
    private Order buildOrder(UUID customerId, List<OrderItem> orderItems, BigDecimal totalAmount) {
        return Order.builder()
                .customerId(customerId)
                .orderItems(orderItems)
                .totalAmount(totalAmount)
                .build();
    }

    /**
     * Restores the stock quantity for existing order items
     *
     * @param existingOrder Order
     */
    private void restoreStockForExistingOrderItems(Order existingOrder) {
        for (OrderItem orderItem : existingOrder.getOrderItems()) {
            Product product = productService.findById(orderItem.getProductId())
                    .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, orderItem.getProductId())));

            // Restore stock quantity
            product.setStockQuantity(product.getStockQuantity() + orderItem.getQuantity());
            productService.update(product);
        }
    }

    //endregion

}
