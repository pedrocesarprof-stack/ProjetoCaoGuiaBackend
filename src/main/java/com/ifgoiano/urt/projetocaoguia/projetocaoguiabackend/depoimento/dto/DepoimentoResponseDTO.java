package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.model.Depoimento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepoimentoResponseDTO {

    private Long id;
    private String descricao;

    public static DepoimentoResponseDTO from(Depoimento depoimento) {
        return DepoimentoResponseDTO.builder()
                .id(depoimento.getId())
                .descricao(depoimento.getDescricao())
                .build();
    }
}