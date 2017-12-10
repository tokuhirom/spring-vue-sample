package com.example.controller.api;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.request.AddProductRequest;
import com.example.response.ProductCreateResponse;
import com.example.response.ProductListResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/product/")
    public ProductListResponse productList() {
        return new ProductListResponse(productRepository.findAll());
    }

    @PostMapping("/api/product/add")
    public ProductCreateResponse addProduct(@Validated @RequestBody AddProductRequest request) {
        Product product = productRepository.addProduct(request.getName());
        return new ProductCreateResponse(product);
    }
}
