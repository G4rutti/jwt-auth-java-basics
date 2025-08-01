package com.auth.service.application;

import com.auth.service.infrastructure.dto.LoginResponseDto;
import com.auth.service.infrastructure.dto.LoginUserDto;

public interface AuthenticationService {
    LoginResponseDto login(LoginUserDto dto);
}
