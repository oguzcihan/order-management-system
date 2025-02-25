package org.cihan.ordermanagementsystem.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.cihan.ordermanagementsystem.order.domain.OrderFilter;
import org.cihan.ordermanagementsystem.order.domain.OrderRequest;
import org.cihan.ordermanagementsystem.order.domain.OrderResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Tag(name = "Order Management", description = "Order Management APIs")
public interface OrderController {

    @Operation(summary = "Add a new order", description = "Adds a new order to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully")
    })
    @PostMapping
    ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest) throws URISyntaxException;

    @Operation(summary = "Get all orders", description = "Retrieves a list of all orders")
    @GetMapping
    ResponseEntity<List<OrderResponse>> getAllOrders(@ParameterObject Pageable pageable);

    @Operation(summary = "Get order by customer ID", description = "Retrieves a order by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<List<OrderResponse>> getOrderByCustomerId(@PathVariable UUID id);

    @Operation(summary = "Delete order", description = "Deletes a order by its ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable UUID id);

    @Operation(summary = "Filter orders", description = "Order data in the startDate and endDate range")
    @GetMapping("/filter")
    ResponseEntity<List<OrderResponse>> findByFilters(@ModelAttribute OrderFilter filter);

}
