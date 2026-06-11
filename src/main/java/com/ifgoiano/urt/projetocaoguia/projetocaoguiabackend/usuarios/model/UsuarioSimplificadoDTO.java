package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO simplificado de usuário para uso em respostas de outras entidades
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSimplificadoDTO {
    private Long id;
    private String nome;
    private String email;

    public static UsuarioSimplificadoDTO from(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioSimplificadoDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}

