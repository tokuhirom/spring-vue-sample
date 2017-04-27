package com.example.controller.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
public class ProductsController {
    @GetMapping("/api/products")
    public ProductListResponse productList() {
        return new ProductListResponse(Arrays.asList(
                new Product("foo"),
                new Product("bar")));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductListResponse {
        List<Product> products;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        String name;
    }
}
