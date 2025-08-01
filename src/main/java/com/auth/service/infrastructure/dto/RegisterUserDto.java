package com.auth.service.infrastructure.dto;

import java.util.Objects;

public class RegisterUserDto {

    private String name;
    private String password;
    private String email;

    public RegisterUserDto(String name, String password, String email) {
        this.name = Objects.requireNonNull(name);
        this.password = Objects.requireNonNull(password);
        this.email = Objects.requireNonNull(email);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
