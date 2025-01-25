package org.cihan.ordermanagementsystem.order.mapper;

import org.cihan.ordermanagementsystem.order.domain.Order;
import org.cihan.ordermanagementsystem.order.domain.OrderItem;
import org.cihan.ordermanagementsystem.order.domain.OrderItemResponse;
import org.cihan.ordermanagementsystem.order.domain.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderMapper {

    /**
     * Order objesini OrderResponse objesine dönüştürür
     * @param order Order
     * @return OrderResponse
     */
    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getOrderItems().stream()
                        .map(OrderMapper::toOrderItemResponse)
                        .collect(Collectors.toList()),
                order.getTotalAmount(),
                order.getCreatedDate()
        );
    }

    /**
     * OrderItem objesini OrderItemResponse objesine dönüştürür
     * @param orderItem OrderItem
     * @return OrderItemResponse
     */
    private static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getTotalPrice()
        );
    }
}
