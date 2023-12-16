package com.moe.orderservice.model;

public record InventoryResponse(
        String skuCode,
        Boolean isInStock
) {
}
