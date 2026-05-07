package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstatisticaResumoDTO {
    private Long noticiaId;
    private Long totalEventos;
    private Long totalVisualizacoes;
    private Long totalLikes;
    private Long totalDeslikes;
    private Long totalCompartilhamentos;
    private Long totalComentarios;
}

