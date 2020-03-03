package com.example.request;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class AddProductRequest {
    @NotNull
    String name;
}
