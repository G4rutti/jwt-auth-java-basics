package com.auth.service.application;

import com.auth.service.infrastructure.dto.RegisterUserDto;
import org.springframework.stereotype.Service;

public interface UserService {
    void register(RegisterUserDto dto);
}
