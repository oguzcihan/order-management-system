package org.cihan.ordermanagementsystem.common.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {
    private static final String PHONE_PATTERN = "^(\\+\\d{1,3})?\\d{10}$";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return phone.matches(PHONE_PATTERN);
    }
}
