package org.cihan.ordermanagementsystem.order.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.cihan.ordermanagementsystem.common.utils.Constants;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemRequest(

        @NotNull(message = Constants.ORDER_REQUEST_PRODUCT_ID_REQUIRED)
        UUID productId,

//        @NotNull(message = Constants.ORDER_REQUEST_ORDER_ID_REQUIRED)
//        UUID orderId,
        @Positive(message = Constants.ORDER_REQUEST_QUANTITY_MUST_BE_GREATER_THAN_ZERO)
        int quantity
//        @Positive(message = Constants.ORDER_REQUEST_PRICE_MUST_BE_GREATER_THAN_ZERO)
//        BigDecimal price,
//        BigDecimal totalPrice
) {}