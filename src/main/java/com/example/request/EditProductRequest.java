package com.example.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EditProductRequest {
    @NotNull
    String name;
}
