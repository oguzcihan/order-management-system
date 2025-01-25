package org.cihan.ordermanagementsystem.customer.repository;

import org.cihan.ordermanagementsystem.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);

    @Override
    long count();
}