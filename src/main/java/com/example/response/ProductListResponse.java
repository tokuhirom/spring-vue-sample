package com.example.response;

import com.example.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Collection;
import java.util.List;

@Value
public class ProductListResponse {
    Collection<Product> products;
}
