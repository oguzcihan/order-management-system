package org.cihan.ordermanagementsystem.product.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.cihan.ordermanagementsystem.common.utils.Constants;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = Constants.PRODUCT_NAME_REQUIRED)
        String name,
        @NotNull(message = Constants.PRODUCT_DESCRIPTION_REQUIRED)
        String description,
        @Positive(message = Constants.PRODUCT_QUANTITY_MUST_BE_GREATER_THAN_ZERO)
        int stockQuantity,
        @Positive(message = Constants.PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO)
        BigDecimal price
) {
}
