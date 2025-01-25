package org.cihan.ordermanagementsystem.order.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        UUID id,
        UUID customerId,
        List<OrderItemResponse> orderItems,
        BigDecimal totalAmount,
        LocalDate createdAt
) {
}
