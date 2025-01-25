package org.cihan.ordermanagementsystem.customer.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.cihan.ordermanagementsystem.common.utils.Constants;

public record CustomerRequest(
        @NotNull(message = Constants.CUSTOMER_FIRSTNAME_REQUIRED)
        String firstName,
        @NotNull(message = Constants.CUSTOMER_LASTNAME_REQUIRED)
        String lastName,
        @NotNull(message = Constants.CUSTOMER_EMAIL_REQUIRED)
        @Email(message = Constants.CUSTOMER_EMAIL_INVALID)
        String email,

        String phone
) {
}
