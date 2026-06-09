package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.Formulario;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    public FormularioResponseDTO salvarFormulario(FormularioRequestDTO dto) {
        if (dto.resposta() == null || dto.resposta().isBlank()) {
            throw new RuntimeException("O conteúdo do formulário não pode estar vazio!");
        }

        Formulario formulario = new Formulario();
        formulario.setDataEnvio(LocalDateTime.now());
        formulario.setResposta(dto.resposta().trim());

        Formulario salvo = formularioRepository.save(formulario);
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
        return new FormularioResponseDTO(atualizado);
    }

    public void deletarFormulario(Long id) {
        Formulario formulario = formularioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado com o ID: " + id));
        formularioRepository.delete(formulario);
    }
}