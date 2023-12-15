package com.moe.orderservice.model;

import java.util.List;

public record CreateOrderRequest(
    List<OrderItemDTO> itemsList

) {
}
