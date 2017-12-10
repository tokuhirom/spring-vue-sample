package com.example.controller.api;

import com.example.exception.NotFoundException;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.request.AddProductRequest;
import com.example.request.EditProductRequest;
import com.example.response.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {
    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/product/")
    public ProductListResponse list() {
        return new ProductListResponse(productRepository.findAll());
    }

    @GetMapping("/api/product/{id}")
    public ProductGetResponse get(@PathVariable("id") Integer id) {
        Product product = productRepository.get(id)
                .orElseThrow(() -> new NotFoundException("Unknown product ID: " + id));
        return new ProductGetResponse(product);
    }

    @PutMapping("/api/product")
    public ProductCreateResponse create(@Validated @RequestBody AddProductRequest request) {
        Product product = productRepository.addProduct(request.getName());
        return new ProductCreateResponse(product);
    }

    @PostMapping("/api/product/{id}")
    public ProductEditResponse edit(@PathVariable("id") Integer id, @Validated @RequestBody EditProductRequest request) {
        Product product = productRepository.edit(
                id,
                request.getName()
        );
        return new ProductEditResponse(product);
    }

    @DeleteMapping("/api/product/{id}")
    public ProductDeleteResponse delete(@PathVariable("id") Integer id) {
        Product product = productRepository.delete(id);
        return new ProductDeleteResponse(product);
    }

}
