package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TreinamentoRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Etapa é obrigatória")
    private String etapa;
}