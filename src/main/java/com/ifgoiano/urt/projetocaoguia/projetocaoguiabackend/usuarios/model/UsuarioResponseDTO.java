package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model;

public record UsuarioResponseDTO(Long id, String nome, String email) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}