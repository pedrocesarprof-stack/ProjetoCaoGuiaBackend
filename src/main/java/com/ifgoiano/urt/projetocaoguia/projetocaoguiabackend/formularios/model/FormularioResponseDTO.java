package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioSimplificadoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormularioResponseDTO {
    private Long id;
    private UsuarioSimplificadoDTO usuario;
    private CategoriaFormulario categoria;
    private String observacao;
    private LocalDateTime dataEnvio;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public FormularioResponseDTO(Formulario formulario) {
        this.id = formulario.getId();
        this.usuario = UsuarioSimplificadoDTO.from(formulario.getUsuario());
        this.categoria = formulario.getCategoria();
        this.observacao = formulario.getObservacao();
        this.dataEnvio = formulario.getDataEnvio();
        this.criadoEm = formulario.getCriadoEm();
        this.atualizadoEm = formulario.getAtualizadoEm();
    }
}