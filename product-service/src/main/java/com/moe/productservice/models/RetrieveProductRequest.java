package com.moe.productservice.models;

import java.math.BigDecimal;

public record RetrieveProductRequest(
        String id,
        String name,
        String description,
        BigDecimal price
) {
}
