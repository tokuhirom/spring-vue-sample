package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("foo"));
        products.add(new Product("bar"));
    }

    public List<Product> findAll() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
