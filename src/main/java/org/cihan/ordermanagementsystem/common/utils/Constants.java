package org.cihan.ordermanagementsystem.common.utils;

public class Constants {
    public static final String CUSTOMER_EMAIL_REQUIRED = "Customer email is required";
    public static final String CUSTOMER_EMAIL_INVALID = "Customer email is not valid";
    public static final String CUSTOMER_FIRSTNAME_REQUIRED = "Customer firstname is required";
    public static final String CUSTOMER_LASTNAME_REQUIRED = "Customer lastname is required";
    public static final String CUSTOMER_NOT_FOUND = "Customer with ID %s not found.";
    public static final String CUSTOMER_EMAIL_ALREADY_EXISTS = "Customer with email %s already exists.";
    public static final String ORDER_NOT_FOUND = "Order with ID %s not found.";
    public static final String PRODUCT_NOT_FOUND = "Product with ID %s not found.";
    public static final String INSUFFICIENT_STOCK = "Insufficient stock for product ID: %s";
    public static final String PRODUCT_NAME_REQUIRED = "Product name is required";
    public static final String PRODUCT_DESCRIPTION_REQUIRED = "Product description is required";
    public static final String PRODUCT_QUANTITY_MUST_BE_GREATER_THAN_ZERO = "Product quantity must be greater than zero";
    public static final String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO = "Product price must be greater than zero";
    public static final String ORDER_REQUEST_PRODUCT_ID_REQUIRED ="Product ID cannot be null";
    public static final String ORDER_REQUEST_ORDER_ID_REQUIRED ="Order ID cannot be null";
    public static final String ORDER_REQUEST_QUANTITY_MUST_BE_GREATER_THAN_ZERO ="Order quantity must be greater than zero";
    public static final String ORDER_REQUEST_PRICE_MUST_BE_GREATER_THAN_ZERO ="Order price must be greater than zero";
    public static final String ORDER_REQUEST_DUPLICATE_ORDER_ITEM ="Duplicate productId found in orderItems: %s";
}
