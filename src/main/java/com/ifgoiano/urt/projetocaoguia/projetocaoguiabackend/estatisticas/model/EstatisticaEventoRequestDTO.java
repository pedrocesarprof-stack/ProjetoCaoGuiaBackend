package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstatisticaEventoRequestDTO {

    @NotNull(message = "noticiaId e obrigatorio")
    private Long noticiaId;

    @NotBlank(message = "usuarioId e obrigatorio")
    private String usuarioId;

    @NotNull(message = "tipoEvento e obrigatorio")
    private TipoEventoEstatistica tipoEvento;

    private String origem;
}

