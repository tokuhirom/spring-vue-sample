package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product(1, "foo"));
        products.add(new Product(2, "bar"));
    }

    public List<Product> findAll() {
        return this.products;
    }

    public Product addProduct(String name) {
        Product product = new Product(
                products.size() + 1,
                name
        );
        this.products.add(product);
        return product;
    }

    public Product edit(Integer id, String name) {
        Product product = new Product(id, name);
        products.set(id - 1, product);
        return product;
    }

    public Optional<Product> get(Integer id) {
        try {
            return Optional.of(this.products.get(id - 1));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
