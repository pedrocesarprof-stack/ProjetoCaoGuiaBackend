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
    private String nome;
    private String email;
    private String telefone;
    private CategoriaFormulario categoria;
    private LocalDateTime dataEnvio;
    private String resposta;
    private UsuarioSimplificadoDTO usuario;
    private UsuarioSimplificadoDTO criadoPor;
    private UsuarioSimplificadoDTO atualizadoPor;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public FormularioResponseDTO(Formulario formulario) {
        this.id = formulario.getId();
        this.nome = formulario.getNome();
        this.email = formulario.getEmail();
        this.telefone = formulario.getTelefone();
        this.categoria = formulario.getCategoria();
        this.dataEnvio = formulario.getDataEnvio();
        this.resposta = formulario.getResposta();
        this.usuario = UsuarioSimplificadoDTO.from(formulario.getUsuario());
        this.criadoPor = UsuarioSimplificadoDTO.from(formulario.getCriadoPor());
        this.atualizadoPor = UsuarioSimplificadoDTO.from(formulario.getAtualizadoPor());
        this.criadoEm = formulario.getCriadoEm();
        this.atualizadoEm = formulario.getAtualizadoEm();
    }
}