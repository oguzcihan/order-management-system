package org.cihan.ordermanagementsystem.product.service;

import org.cihan.ordermanagementsystem.product.domain.Product;
import org.cihan.ordermanagementsystem.product.domain.ProductRequest;
import org.cihan.ordermanagementsystem.product.domain.ProductResponse;
import org.cihan.ordermanagementsystem.product.mapper.ProductMapper;
import org.cihan.ordermanagementsystem.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_product() {
        ProductRequest productRequest = new ProductRequest(
                "Test Product",
                "Test Description",
                10,
                BigDecimal.valueOf(100.0)
        );

        Product product = new Product(
                null,
                "Test Product",
                "Test Description",
                10,
                BigDecimal.valueOf(100.0)
        );

        Product savedProduct = new Product(
                UUID.randomUUID(),
                "Test Product",
                "Test Description",
                10,
                BigDecimal.valueOf(100.0)
        );

        Mockito.when(productMapper.toProduct(productRequest))
                .thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(savedProduct);
        Mockito.when(productMapper.toProductResponse(savedProduct))
                .thenReturn(new ProductResponse(
                        null,
                        "Test Product",
                        "Test Description",
                        10,
                        BigDecimal.valueOf(100.0),
                        LocalDate.now()
                ));

        ProductResponse productResponse = productService.createProduct(productRequest);

        //Then
        assertNotNull(productResponse);
        assertEquals(productRequest.name(), productResponse.name());
        assertEquals(productRequest.description(), productResponse.description());
        assertEquals(productRequest.stockQuantity(), productResponse.stockQuantity());
        assertEquals(productRequest.price(), productResponse.price());
    }
}