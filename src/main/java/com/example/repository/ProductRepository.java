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
        products.add(new Product(1L, "foo"));
        products.add(new Product(2L, "bar"));
    }

    public List<Product> findAll() {
        return this.products;
    }

    public Product addProduct(String name) {
        Product product = new Product(
                (long) (products.size() + 1),
                name
        );
        this.products.add(product);
        return product;
    }
}
