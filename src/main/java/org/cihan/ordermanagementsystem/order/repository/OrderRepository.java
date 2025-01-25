package org.cihan.ordermanagementsystem.order.repository;

import org.cihan.ordermanagementsystem.order.domain.Order;
import org.cihan.ordermanagementsystem.order.domain.OrderFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCustomerId(UUID customerId);


    @Query(value = "SELECT * FROM Orders p WHERE " +
            "(COALESCE(:#{#filter.startDate}, DATE(p.created_date)) = DATE(p.created_date) OR DATE(p.created_date) >= :#{#filter.startDate}) AND " +
            "(COALESCE(:#{#filter.endDate}, DATE(p.created_date)) = DATE(p.created_date) OR DATE(p.created_date) <= :#{#filter.endDate})",
            nativeQuery = true)
    List<Order> findByFilters(@Param("filter") OrderFilter filter);
}