package com.moe.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="t_order_items")
public class OrderLineItems {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private UUID orderNumber;
    private String skuCode;
    private BigDecimal price;
    private  Integer quantity;

}
