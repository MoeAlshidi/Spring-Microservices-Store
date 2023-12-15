package com.moe.orderservice.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(

        Long id,
        UUID orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
