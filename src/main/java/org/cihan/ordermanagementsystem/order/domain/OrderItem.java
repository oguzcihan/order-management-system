package org.cihan.ordermanagementsystem.order.domain;

import jakarta.persistence.*;
import lombok.*;
import org.cihan.ordermanagementsystem.order.domain.Order;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

}
