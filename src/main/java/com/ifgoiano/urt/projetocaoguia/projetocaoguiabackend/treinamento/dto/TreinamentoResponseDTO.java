package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.model.Treinamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreinamentoResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String etapa;

    public static TreinamentoResponseDTO from(Treinamento treinamento) {
        return TreinamentoResponseDTO.builder()
                .id(treinamento.getId())
                .titulo(treinamento.getTitulo())
                .descricao(treinamento.getDescricao())
                .etapa(treinamento.getEtapa())
                .build();
    }
}