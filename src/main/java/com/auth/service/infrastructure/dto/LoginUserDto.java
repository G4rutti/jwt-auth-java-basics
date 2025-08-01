package com.auth.service.infrastructure.dto;

import java.util.Objects;

public class LoginUserDto {
    private String password;
    private String email;

    public LoginUserDto(String password, String email) {
        this.password = Objects.requireNonNull(password);
        this.email = Objects.requireNonNull(email);
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
