package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TreinamentoRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 150, message = "Título deve ter no máximo 150 caracteres")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 2000, message = "Descrição deve ter no máximo 2000 caracteres")
    private String descricao;

    @NotBlank(message = "Etapa é obrigatória")
    @Size(max = 100, message = "Etapa deve ter no máximo 100 caracteres")
    private String etapa;
}