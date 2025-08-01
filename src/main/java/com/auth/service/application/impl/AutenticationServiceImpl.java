package com.auth.service.application.impl;

import com.auth.service.application.AuthenticationService;
import com.auth.service.application.TokenService;
import com.auth.service.domain.User;
import com.auth.service.infrastructure.dto.LoginResponseDto;
import com.auth.service.infrastructure.dto.LoginUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AutenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    // Injeta as dependências necessárias para autenticar e gerar token
    public AutenticationServiceImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponseDto login(LoginUserDto dto) {
        // 1. Cria um objeto de autenticação com as credenciais do DTO
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        // 2. Chama o AuthenticationManager para validar.
        // É AQUI que o Spring chama seu UserDetailsService e verifica a senha.
        // Se as credenciais estiverem erradas, ele lança uma exceção aqui.
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // 3. Se a autenticação foi bem-sucedida, o `auth` contém os dados do usuário.
        //    Geramos o token com base nesses dados.
        var token = tokenService.generateToken((User) auth.getPrincipal());

        // 4. Retorna o DTO de resposta com o token.
        return new LoginResponseDto(token);
    }

}
