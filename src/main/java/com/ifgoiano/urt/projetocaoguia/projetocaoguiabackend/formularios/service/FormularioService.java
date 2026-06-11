package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.service;

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

    public FormularioResponseDTO salvarFormulario(FormularioRequestDTO dto) {
        if (dto.resposta() == null || dto.resposta().isBlank()) {
            throw new RuntimeException("O conteúdo do formulário não pode estar vazio!");
        }

        Formulario formulario = new Formulario();
        formulario.setDataEnvio(LocalDateTime.now());
        formulario.setResposta(dto.resposta().trim());

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
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado com o ID: " + id));
        estatisticaService.registrarEventoInterno(id, TipoEntidade.FORMULARIO, TipoEventoEstatistica.VISUALIZACAO);
        return new FormularioResponseDTO(formulario);
    }

    public FormularioResponseDTO atualizarResposta(Long id, FormularioRequestDTO dto) {
        if (dto.resposta() == null || dto.resposta().isBlank()) {
            throw new RuntimeException("O conteúdo da resposta não pode estar vazio!");
        }

        Formulario formulario = formularioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado com o ID: " + id));

        formulario.setResposta(dto.resposta().trim());
        Formulario atualizado = formularioRepository.save(formulario);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.FORMULARIO, TipoEventoEstatistica.ATUALIZACAO);
        return new FormularioResponseDTO(atualizado);
    }

    public void deletarFormulario(Long id) {
        Formulario formulario = formularioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado com o ID: " + id));
        formularioRepository.delete(formulario);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.FORMULARIO, TipoEventoEstatistica.EXCLUSAO);
    }
}