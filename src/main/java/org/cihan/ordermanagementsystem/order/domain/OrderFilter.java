package org.cihan.ordermanagementsystem.order.domain;

import java.time.LocalDate;

public record OrderFilter(
        LocalDate startDate,
        LocalDate endDate
) {
}
