package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model;

public record AdminRequestDTO(
        String nome,
        String email,
        String telefone,
        String senha
) {
    public AdminRequestDTO {
        if (nome != null) nome = nome.trim();
        if (email != null) email = email.trim().toLowerCase();
        if (telefone != null) telefone = telefone.trim();
        if (senha != null) senha = senha.trim();
    }
}

