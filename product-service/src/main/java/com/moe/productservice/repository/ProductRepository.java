package com.moe.productservice.repository;

import com.moe.productservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface ProductRepository extends MongoRepository<Product, String> {
}
