package org.cihan.ordermanagementsystem.customer.domain;

import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone

) {
}
