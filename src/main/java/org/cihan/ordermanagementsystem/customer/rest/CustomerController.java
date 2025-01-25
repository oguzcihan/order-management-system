package org.cihan.ordermanagementsystem.customer.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.cihan.ordermanagementsystem.customer.domain.CustomerRequest;
import org.cihan.ordermanagementsystem.customer.domain.CustomerResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Customer Management", description = "Customer Management APIs")
public interface CustomerController {

    @Operation(summary = "Create a new customer")
    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) throws URISyntaxException;

    @Operation(summary = "Retrieve all customers with pagination")
    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAllCustomers(@ParameterObject Pageable pageable);

    @Operation(summary = "Retrieve a customer by ID")
    @GetMapping("/{id}")
    ResponseEntity<Optional<CustomerResponse>> getCustomerById(@PathVariable UUID id);

    @Operation(summary = "Update an existing customer")
    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID id, @RequestBody @Valid CustomerRequest customerRequest);

    @Operation(summary = "Delete a customer by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable UUID id);
}

