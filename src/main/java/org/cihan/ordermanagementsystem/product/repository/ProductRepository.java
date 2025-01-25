package org.cihan.ordermanagementsystem.product.repository;

import org.cihan.ordermanagementsystem.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
}
