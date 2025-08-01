package com.auth.service.infrastructure.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class ProtectedDataController {

    @GetMapping("/me")
    public String me() {
        return "Olá, usuário autenticado!";
    }
}
