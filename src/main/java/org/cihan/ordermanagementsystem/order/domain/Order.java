package org.cihan.ordermanagementsystem.order.domain;

import jakarta.persistence.*;
import lombok.*;
import org.cihan.ordermanagementsystem.common.model.AbstractEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID customerId;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    private BigDecimal totalAmount;
}
