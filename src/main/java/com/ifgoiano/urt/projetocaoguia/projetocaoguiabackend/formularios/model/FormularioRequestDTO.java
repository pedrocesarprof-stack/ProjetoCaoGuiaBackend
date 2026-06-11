package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model;

public record FormularioRequestDTO(
        String nome,
        String email,
        String telefone,
        CategoriaFormulario categoria,
        String resposta
) {
}