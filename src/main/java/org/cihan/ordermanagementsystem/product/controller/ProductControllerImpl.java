package org.cihan.ordermanagementsystem.product.controller;

import jakarta.validation.Valid;
import org.cihan.ordermanagementsystem.common.utils.PaginationUtil;
import org.cihan.ordermanagementsystem.product.service.ProductService;
import org.cihan.ordermanagementsystem.product.domain.ProductRequest;
import org.cihan.ordermanagementsystem.product.domain.ProductResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }


    @Override
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) throws URISyntaxException {
        ProductResponse result = productService.createProduct(productRequest);
        return ResponseEntity.created(new URI("/api/v1/products/" + result.id())).body(result);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts(@ParameterObject Pageable pageable) {
        Page<ProductResponse> productsPage = productService.getAllProducts(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), productsPage);

        return ResponseEntity.ok()
                .headers(headers).body(productsPage.getContent());
    }

    @Override
    public ResponseEntity<Optional<ProductResponse>> getProductById(UUID id) {
        Optional<ProductResponse> result = productService.findProductById(id);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(UUID id, @RequestBody @Valid ProductRequest productRequest) {
        ProductResponse result = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }
}
