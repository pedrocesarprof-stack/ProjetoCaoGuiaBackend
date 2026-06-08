package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepoimentoRequestDTO {

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 3000, message = "Descrição deve ter no máximo 3000 caracteres")
    private String descricao;
}