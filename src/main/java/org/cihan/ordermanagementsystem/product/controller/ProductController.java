package org.cihan.ordermanagementsystem.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.cihan.ordermanagementsystem.product.domain.ProductRequest;
import org.cihan.ordermanagementsystem.product.domain.ProductResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Product Management", description = "Product Management APIs")
public interface ProductController {

    @Operation(summary = "Add a new product", description = "Adds a new product to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully")
    })
    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) throws URISyntaxException;

    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    @GetMapping
    ResponseEntity<List<ProductResponse>> getAllProducts(@ParameterObject Pageable pageable);

    @Operation(summary = "Get product by ID", description = "Retrieves a product by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable UUID id);


    @Operation(summary = "Update product", description = "Updates an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRequest productRequest);

    @Operation(summary = "Delete product", description = "Deletes a product by its ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable UUID id);
}
