package org.cihan.ordermanagementsystem.order.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.cihan.ordermanagementsystem.common.utils.Constants;

import java.util.UUID;

public record OrderItemRequest(

        @NotNull(message = Constants.ORDER_REQUEST_PRODUCT_ID_REQUIRED)
        UUID productId,

        @Positive(message = Constants.ORDER_REQUEST_QUANTITY_MUST_BE_GREATER_THAN_ZERO)
        int quantity

) {}