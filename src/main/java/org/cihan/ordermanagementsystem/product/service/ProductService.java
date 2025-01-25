package org.cihan.ordermanagementsystem.product.service;

import lombok.RequiredArgsConstructor;
import org.cihan.ordermanagementsystem.common.exception.NotFoundException;
import org.cihan.ordermanagementsystem.common.utils.Constants;
import org.cihan.ordermanagementsystem.product.mapper.ProductMapper;
import org.cihan.ordermanagementsystem.product.repository.ProductRepository;
import org.cihan.ordermanagementsystem.product.domain.Product;
import org.cihan.ordermanagementsystem.product.domain.ProductRequest;
import org.cihan.ordermanagementsystem.product.domain.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("createProduct post operation called");
        var savedProduct = productRepository.save(productMapper.toProduct(productRequest));
        return productMapper.toProductResponse(savedProduct);
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        log.info("getAllProducts called");
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(productMapper::toProductResponse);
    }

    public Optional<ProductResponse> findProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, id)));
        return Optional.ofNullable(productMapper.toProductResponse(product));
    }

    public ProductResponse updateProduct(UUID id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, id)));
        existingProduct.setName(productRequest.name());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setPrice(productRequest.price());
        existingProduct.setStockQuantity(productRequest.stockQuantity());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toProductResponse(updatedProduct);
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, id));
        }
        productRepository.deleteById(id);
    }

    /**
     * Request, Response olmadan direkt olarak entity ile update eder.
     * @param product Product
     */
    public void update(Product product) {
        log.info("updateQuantity called for orderservice");
        productRepository.save(product);
    }

    /**
     * Request, Response olmadan direkt olarak entity döner.
     * @param id UUID
     * @return Product
     */
    public Optional<Product> findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_NOT_FOUND, id)));
        return Optional.ofNullable(product);
    }

}
