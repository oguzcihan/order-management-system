package org.cihan.ordermanagementsystem.order.service;

import lombok.RequiredArgsConstructor;
import org.cihan.ordermanagementsystem.order.domain.OrderItem;
import org.cihan.ordermanagementsystem.order.domain.OrderItemRequest;
import org.cihan.ordermanagementsystem.order.domain.OrderItemResponse;
import org.cihan.ordermanagementsystem.order.mapper.OrderItemMapper;
import org.cihan.ordermanagementsystem.order.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;


    /**
     * Sipariş elemanlarını kaydetmek icin kullanılır
     * @param orderItems List<OrderItem>
     * @return List<OrderItemResponse>
     */
    public List<OrderItemResponse> saveOrderItems(List<OrderItem> orderItems) {
        List<OrderItem> savedAllOrderItem = orderItemRepository.saveAll(orderItems);
        return orderItemMapper.toOrderItemsResponse(savedAllOrderItem);
    }
}
