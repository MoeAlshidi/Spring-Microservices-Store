package com.moe.orderservice.model;

import java.util.List;
import java.util.UUID;

public record RetrieveOrderDTO(
        Long id,
        UUID orderNumber,
        List<OrderItemDTO> orderLineItemsList


) {
}
