package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.EntityNotFoundException;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.security.AuthenticationFacade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEntidade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEventoEstatistica;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service.EstatisticaService;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.Formulario;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.repository.FormularioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormularioService {

    private final FormularioRepository formularioRepository;
    private final EstatisticaService estatisticaService;
    private final AuthenticationFacade authenticationFacade;

    public FormularioResponseDTO salvarFormulario(FormularioRequestDTO dto) {
        // Validações
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new RuntimeException("O nome não pode estar vazio!");
        }
        if (dto.email() == null || dto.email().isBlank()) {
            throw new RuntimeException("O email não pode estar vazio!");
        }
        if (dto.categoria() == null) {
            throw new RuntimeException("A categoria deve ser informada!");
        }
        if (dto.resposta() == null || dto.resposta().isBlank()) {
            throw new RuntimeException("O conteúdo do formulário não pode estar vazio!");
        }

        var usuarioLogado = authenticationFacade.getAuthenticatedUserOrNull();

        Formulario formulario = Formulario.builder()
                .nome(dto.nome().trim())
                .email(dto.email().trim().toLowerCase())
                .telefone(dto.telefone() != null ? dto.telefone().trim() : null)
                .categoria(dto.categoria())
                .dataEnvio(LocalDateTime.now())
                .resposta(dto.resposta().trim())
                .usuario(usuarioLogado)
                .criadoPor(usuarioLogado)
                .atualizadoPor(usuarioLogado)
                .build();

        Formulario salvo = formularioRepository.save(formulario);
        estatisticaService.registrarEventoInterno(salvo.getId(), TipoEntidade.FORMULARIO, TipoEventoEstatistica.CRIACAO);
        return new FormularioResponseDTO(salvo);
    }

    public List<FormularioResponseDTO> listarTodos() {
        return formularioRepository.findAll().stream()
                .map(FormularioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public FormularioResponseDTO buscarPorId(Long id) {
        Formulario formulario = formularioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formulário", id));
        estatisticaService.registrarEventoInterno(id, TipoEntidade.FORMULARIO, TipoEventoEstatistica.VISUALIZACAO);
        return new FormularioResponseDTO(formulario);
    }

    public FormularioResponseDTO atualizarResposta(Long id, FormularioRequestDTO dto) {
        var usuarioLogado = authenticationFacade.getAuthenticatedUserOrNull();

        Formulario formulario = formularioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formulário", id));

        if (dto.nome() != null && !dto.nome().isBlank()) {
            formulario.setNome(dto.nome().trim());
        }
        if (dto.email() != null && !dto.email().isBlank()) {
            formulario.setEmail(dto.email().trim().toLowerCase());
        }
        if (dto.telefone() != null) {
            formulario.setTelefone(dto.telefone().trim());
        }
        if (dto.categoria() != null) {
            formulario.setCategoria(dto.categoria());
        }
        if (dto.resposta() != null && !dto.resposta().isBlank()) {
            formulario.setResposta(dto.resposta().trim());
        }

        formulario.setAtualizadoPor(usuarioLogado);

        Formulario atualizado = formularioRepository.save(formulario);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.FORMULARIO, TipoEventoEstatistica.ATUALIZACAO);
        return new FormularioResponseDTO(atualizado);
    }

    public void deletarFormulario(Long id) {
        Formulario formulario = formularioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formulário", id));
        formularioRepository.delete(formulario);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.FORMULARIO, TipoEventoEstatistica.EXCLUSAO);
    }
}