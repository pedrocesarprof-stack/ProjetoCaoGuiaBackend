package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.model.Depoimento;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioSimplificadoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DepoimentoResponseDTO {

    private Long id;
    private String descricao;
    private UsuarioSimplificadoDTO criadoPor;
    private UsuarioSimplificadoDTO atualizadoPor;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public static DepoimentoResponseDTO from(Depoimento depoimento) {
        return DepoimentoResponseDTO.builder()
                .id(depoimento.getId())
                .descricao(depoimento.getDescricao())
                .criadoPor(UsuarioSimplificadoDTO.from(depoimento.getCriadoPor()))
                .atualizadoPor(UsuarioSimplificadoDTO.from(depoimento.getAtualizadoPor()))
                .criadoEm(depoimento.getCriadoEm())
                .atualizadoEm(depoimento.getAtualizadoEm())
                .build();
    }
}