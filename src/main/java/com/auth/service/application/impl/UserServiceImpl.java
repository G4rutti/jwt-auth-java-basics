
package com.auth.service.application.impl;
import com.auth.service.application.UserService;

import com.auth.service.domain.User;
import com.auth.service.infrastructure.dto.RegisterUserDto;
import com.auth.service.infrastructure.persistence.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Anotação para o Spring gerenciar esta classe
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependências via construtor (melhor prática)
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Implementação do método da SUA interface
    @Override
    public void register(RegisterUserDto dto) {
        // Lógica para verificar se o usuário já existe, etc.
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        User newUser = new User(dto.getName(), hashedPassword ,dto.getEmail() );
        userRepository.save(newUser);
    }

    // Implementação do método da interface do SPRING SECURITY
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // O Spring Security chama este método. O "username" aqui é o e-mail.
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }
}