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
    private LocalDateTime dataEnvio;
    private String resposta;
    private UsuarioSimplificadoDTO criadoPor;
    private UsuarioSimplificadoDTO atualizadoPor;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public FormularioResponseDTO(Formulario formulario) {
        this.id = formulario.getId();
        this.dataEnvio = formulario.getDataEnvio();
        this.resposta = formulario.getResposta();
        this.criadoPor = UsuarioSimplificadoDTO.from(formulario.getCriadoPor());
        this.atualizadoPor = UsuarioSimplificadoDTO.from(formulario.getAtualizadoPor());
        this.criadoEm = formulario.getCriadoEm();
        this.atualizadoEm = formulario.getAtualizadoEm();
    }
}