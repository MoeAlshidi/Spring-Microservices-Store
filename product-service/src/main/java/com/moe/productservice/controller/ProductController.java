package com.moe.productservice.controller;

import com.moe.productservice.models.CreateProductRequest;
import com.moe.productservice.models.RetrieveProductRequest;
import com.moe.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@Valid @RequestBody CreateProductRequest request){
        String productId = productService.createProduct(request);
        return productId;
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public RetrieveProductRequest getProductById(@PathVariable String id){
        return productService.getProductByID(id);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RetrieveProductRequest> getProducts(){
        return productService.getAllProducts();
    }
    

}
