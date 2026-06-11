package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstatisticaResumoDTO {
    private Long entidadeId;
    private TipoEntidade tipoEntidade;
    private Long totalEventos;
    private Long totalVisualizacoes;
    private Long totalLikes;
    private Long totalDeslikes;
    private Long totalCompartilhamentos;
    private Long totalComentarios;
    private Long totalCriacoes;
    private Long totalAtualizacoes;
    private Long totalExclusoes;
}
