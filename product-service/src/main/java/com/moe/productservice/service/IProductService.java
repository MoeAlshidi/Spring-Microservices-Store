package com.moe.productservice.service;

import com.moe.productservice.models.CreateProductRequest;
import com.moe.productservice.models.RetrieveProductRequest;


import java.util.List;


public interface IProductService {
    String createProduct(CreateProductRequest request);
    RetrieveProductRequest getProductByID(String id);
    List<RetrieveProductRequest> getAllProducts();

}
