package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

@Value
@AllArgsConstructor(onConstructor = @__({ @JsonCreator }))
public class EditProductRequest {
    @NotNull
    String name;
}
