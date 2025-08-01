package com.auth.service.infrastructure.web;

import com.auth.service.application.TokenService;
import com.auth.service.application.UserService;
import com.auth.service.infrastructure.dto.LoginResponseDto;
import com.auth.service.infrastructure.dto.LoginUserDto;
import com.auth.service.infrastructure.dto.RegisterUserDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(UserService userService,
                                    TokenService tokenService,
                                    AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterUserDto dto) {
        userService.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginUserDto dto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(login);

        String token = tokenService.generateToken((com.auth.service.domain.User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
