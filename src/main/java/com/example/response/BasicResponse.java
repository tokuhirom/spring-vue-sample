package com.example.response;

import lombok.Value;

@Value
public class BasicResponse {
    String message;

    public BasicResponse() {
        this.message = null;
    }

    public BasicResponse(String message) {
        this.message = message;
    }
}
