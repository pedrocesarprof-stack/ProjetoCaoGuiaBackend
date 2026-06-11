package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.model;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioSimplificadoDTO;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NoticiaResponseDTO {

    private Long id;
    private String titulo;
    private String conteudo;
    private String resumo;
    private String autor;
    private CategoriaNoticia categoria;
    private StatusNoticia status;
    private String tags;
    private String imagemUrl;
    private UsuarioSimplificadoDTO criadoPor;
    private UsuarioSimplificadoDTO atualizadoPor;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private LocalDateTime publicadoEm;

    public static NoticiaResponseDTO from(Noticia n) {
        return NoticiaResponseDTO.builder()
                .id(n.getId())
                .titulo(n.getTitulo())
                .conteudo(n.getConteudo())
                .resumo(n.getResumo())
                .autor(n.getAutor())
                .categoria(n.getCategoria())
                .status(n.getStatus())
                .tags(n.getTags())
                .imagemUrl(n.getImagemUrl())
                .criadoPor(UsuarioSimplificadoDTO.from(n.getCriadoPor()))
                .atualizadoPor(UsuarioSimplificadoDTO.from(n.getAtualizadoPor()))
                .criadoEm(n.getCriadoEm())
                .atualizadoEm(n.getAtualizadoEm())
                .publicadoEm(n.getPublicadoEm())
                .build();
    }
}

