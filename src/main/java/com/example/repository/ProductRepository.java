package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {
    private final Map<Integer, Product> products;
    private final AtomicInteger idCounter;

    public ProductRepository() {
        idCounter = new AtomicInteger(0);
        products = new ConcurrentHashMap<>();
        addProduct("foo");
        addProduct("bar");
        products.put(1, new Product(1, "foo"));
        products.put(2, new Product(2, "bar"));
    }

    public Collection<Product> findAll() {
        return this.products.values();
    }

    public Product addProduct(String name) {
        Integer id = idCounter.incrementAndGet();
        Product product = new Product(
                id,
                name
        );
        this.products.put(id, product);
        return product;
    }

    public Product edit(Integer id, String name) {
        Product product = new Product(id, name);
        products.put(id, product);
        return product;
    }

    public Optional<Product> get(Integer id) {
        return Optional.ofNullable(this.products.get(id));
    }

    public Product delete(Integer id) {
        return this.products.remove(id);
    }
}
