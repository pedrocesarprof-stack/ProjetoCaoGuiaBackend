package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model;

public enum CategoriaFormulario {
    CEGO("Pessoa Cega"),
    FAMILIA_ACOLHEDORA("Família Acolhedora"),
    SOCIALIZADORA("Socializadora"),
    DOACAO("Doação");

    private final String descricao;

    CategoriaFormulario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

