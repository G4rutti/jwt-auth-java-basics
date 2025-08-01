package com.auth.service.application;
import com.auth.service.domain.User;

public interface TokenService {
    String generateToken(User dto);
    String validateTokenAndGetSubject(String token);
}
