package org.cihan.ordermanagementsystem.order.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponse(
        UUID id,
        UUID productId,
        int quantity,
        BigDecimal price,
        BigDecimal totalPrice
) {}