package com.moe.inventoryservice.model;

public record InventoryResponse(
        String skuCode,
        Boolean isInStock
) {
}
