
package com.moe.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_order")
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private UUID orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_items"
    )
    private List<OrderLineItems> orderLineItemsList;



}
