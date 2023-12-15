package com.moe.productservice.mapper;

import com.moe.productservice.models.Product;
import com.moe.productservice.models.RetrieveProductRequest;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductMapper implements Function<Product, RetrieveProductRequest> {
    @Override
    public RetrieveProductRequest apply(Product product) {
        return new RetrieveProductRequest(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
