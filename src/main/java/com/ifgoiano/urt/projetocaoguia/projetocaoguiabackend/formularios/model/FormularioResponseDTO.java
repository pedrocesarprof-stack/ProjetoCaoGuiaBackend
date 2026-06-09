package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model;

import java.time.LocalDateTime;

public record FormularioResponseDTO(Long id, LocalDateTime dataEnvio, String resposta) {
    public FormularioResponseDTO(Formulario formulario) {
        this(formulario.getId(), formulario.getDataEnvio(), formulario.getResposta());
    }
}