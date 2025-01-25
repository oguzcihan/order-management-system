package org.cihan.ordermanagementsystem.order.mapper;

import org.cihan.ordermanagementsystem.order.domain.Order;
import org.cihan.ordermanagementsystem.order.domain.OrderItem;
import org.cihan.ordermanagementsystem.order.domain.OrderItemRequest;
import org.cihan.ordermanagementsystem.order.domain.OrderItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemMapper {
    public OrderItem toOrderItem(OrderItemRequest orderItemRequest) {
        return OrderItem.builder()
                .quantity(orderItemRequest.quantity())
                .productId(orderItemRequest.productId())
                .build();
    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getTotalPrice()
        );
    }

    public OrderItemRequest toOrderItemRequest(OrderItem orderItem) {
        return new OrderItemRequest(
                orderItem.getProductId(),
                orderItem.getQuantity()
        );
    }

    public OrderItem toOrderItemFromOrderItemResponse(OrderItemResponse orderItemResponse) {
        return OrderItem.builder()
                .id(orderItemResponse.id())
                .productId(orderItemResponse.productId())
                .quantity(orderItemResponse.quantity())
                .price(orderItemResponse.price())
                .totalPrice(orderItemResponse.totalPrice())
                .build();
    }

    public List<OrderItemResponse> toOrderItemsResponse(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::toOrderItemResponse)
                .collect(Collectors.toList());
    }

    public List<OrderItem> toOrderItemsFromOrderItemResponse(List<OrderItemResponse> orderItemResponse) {
        return orderItemResponse.stream()
                .map(this::toOrderItemFromOrderItemResponse)
                .collect(Collectors.toList());
    }
}
