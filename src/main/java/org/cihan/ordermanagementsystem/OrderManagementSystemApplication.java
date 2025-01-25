package org.cihan.ordermanagementsystem;

import org.cihan.ordermanagementsystem.customer.domain.CustomerRequest;
import org.cihan.ordermanagementsystem.customer.mapper.CustomerMapper;
import org.cihan.ordermanagementsystem.customer.service.CustomerService;
import org.cihan.ordermanagementsystem.order.domain.OrderRequest;
import org.cihan.ordermanagementsystem.order.service.OrderService;
import org.cihan.ordermanagementsystem.product.domain.ProductRequest;
import org.cihan.ordermanagementsystem.product.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;

@SpringBootApplication
public class OrderManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementSystemApplication.class, args);
    }
}
