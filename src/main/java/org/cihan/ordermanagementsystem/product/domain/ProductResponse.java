package org.cihan.ordermanagementsystem.product.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        int stockQuantity,
        BigDecimal price,
        LocalDate createdDate
) {
}
