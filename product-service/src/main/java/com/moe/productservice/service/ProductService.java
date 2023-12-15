package com.moe.productservice.service;

import com.moe.productservice.exception.ResourceNotFoundException;
import com.moe.productservice.mapper.ProductMapper;
import com.moe.productservice.models.CreateProductRequest;
import com.moe.productservice.models.Product;
import com.moe.productservice.models.RetrieveProductRequest;
import com.moe.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService  implements  IProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public String createProduct(CreateProductRequest request) {
        Product product = new Product(
                request.name(),
                request.description(),
                request.price());
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return product.getId();
    }

    @Override
    public RetrieveProductRequest getProductByID(String id) {
      Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
      RetrieveProductRequest retrieveProduct = new RetrieveProductRequest(
              product.getId(),
              product.getName(),
              product.getDescription(),
              product.getPrice()
      );
        log.info("Product {} is retrieved", product.getId());

        return retrieveProduct;
    }

    @Override
    public List<RetrieveProductRequest> getAllProducts() {
        List<Product> products = productRepository.findAll();
        log.info("List of Products are retrieved");
        return products.stream().map(productMapper).collect(Collectors.toList());
    }

}
