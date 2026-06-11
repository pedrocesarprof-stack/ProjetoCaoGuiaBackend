package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.model.Treinamento;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioSimplificadoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TreinamentoResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String etapa;
    private UsuarioSimplificadoDTO criadoPor;
    private UsuarioSimplificadoDTO atualizadoPor;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public static TreinamentoResponseDTO from(Treinamento treinamento) {
        return TreinamentoResponseDTO.builder()
                .id(treinamento.getId())
                .titulo(treinamento.getTitulo())
                .descricao(treinamento.getDescricao())
                .etapa(treinamento.getEtapa())
                .criadoPor(UsuarioSimplificadoDTO.from(treinamento.getCriadoPor()))
                .atualizadoPor(UsuarioSimplificadoDTO.from(treinamento.getAtualizadoPor()))
                .criadoEm(treinamento.getCriadoEm())
                .atualizadoEm(treinamento.getAtualizadoEm())
                .build();
    }
}