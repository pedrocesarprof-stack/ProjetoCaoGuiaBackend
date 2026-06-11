package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstatisticaEventoRequestDTO {

    @NotNull(message = "entidadeId é obrigatório")
    private Long entidadeId;

    @NotNull(message = "tipoEntidade é obrigatório")
    private TipoEntidade tipoEntidade;

    private String usuarioId;

    @NotNull(message = "tipoEvento é obrigatório")
    private TipoEventoEstatistica tipoEvento;

    private String origem;
}
