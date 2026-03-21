package com.example.api.model;

import jakarta.validation.constraints.NotBlank;

public class GreetRequest {

    @NotBlank(message = "name must not be blank")
    private String name;

    public GreetRequest() {
    }

    public GreetRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
