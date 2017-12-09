package com.example.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddProductRequest {
    @NotNull
    String name;
}
