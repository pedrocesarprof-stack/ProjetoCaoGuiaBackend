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
        if (dto.categoria() == null) {
            throw new RuntimeException("A categoria deve ser informada!");
        }
        if (dto.observacao() == null || dto.observacao().isBlank()) {
            throw new RuntimeException("A observação não pode estar vazia!");
        }

        var usuarioLogado = authenticationFacade.getAuthenticatedUserOrNull();

        // Se não houver usuário autenticado, não permite envio
        if (usuarioLogado == null) {
            throw new RuntimeException("É necessário estar autenticado para enviar um formulário!");
        }

        Formulario formulario = Formulario.builder()
                .nome(usuarioLogado.getNome())
                .email(usuarioLogado.getEmail())
                .telefone(usuarioLogado.getTelefone())
                .categoria(dto.categoria())
                .dataEnvio(LocalDateTime.now())
                .observacao(dto.observacao().trim())
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

        if (dto.categoria() != null) {
            formulario.setCategoria(dto.categoria());
        }
        if (dto.observacao() != null && !dto.observacao().isBlank()) {
            formulario.setObservacao(dto.observacao().trim());
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