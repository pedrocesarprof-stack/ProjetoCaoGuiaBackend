package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model;

public record UsuarioRequestDTO(String nome, String email, String senha) {
    public UsuarioRequestDTO {
        if (nome != null) nome = nome.trim();
        if (email != null) email = email.trim().toLowerCase();
        if (senha != null) senha = senha.trim();
    }
}