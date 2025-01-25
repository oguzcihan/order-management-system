package org.cihan.ordermanagementsystem.product.mapper;

import org.cihan.ordermanagementsystem.product.domain.Product;
import org.cihan.ordermanagementsystem.product.domain.ProductRequest;
import org.cihan.ordermanagementsystem.product.domain.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    /**
     * ProductRequest objectini Product objectine dönüştürür
     * @param productRequest ProductRequest
     * @return Product
     */
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .stockQuantity(productRequest.stockQuantity())
                .price(productRequest.price())
                .build();
    }

    /**
     * Product objectini ProductResponse objectine dönüştürür
     * @param product Product
     * @return ProductResponse
     */
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStockQuantity(),
                product.getPrice(),
                product.getCreatedDate()
        );
    }
}
