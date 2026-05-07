package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoticiaRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Conteúdo é obrigatório")
    private String conteudo;

    private String resumo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotNull(message = "Categoria é obrigatória")
    private CategoriaNoticia categoria;

    private StatusNoticia status;

    private String tags;

    private String imagemUrl;
}

