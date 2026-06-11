package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.service.FormularioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formularios")
@RequiredArgsConstructor
@Tag(
        name = "Formulários",
        description = "Gerenciamento dos formulários de contato do Projeto Cão-Guia Digital"
)
public class FormularioController {

    private final FormularioService formularioService;

    @PostMapping
    @Operation(summary = "Enviar formulário")
    public ResponseEntity<FormularioResponseDTO> enviarFormulario(
            @RequestBody FormularioRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(formularioService.salvarFormulario(dto));
    }

    @GetMapping
    @Operation(
            summary = "Listar formulários",
            description = "Retorna a lista de todos os formulários enviados"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FormularioResponseDTO>> listarFormularios() {
        return ResponseEntity.ok(formularioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar formulário por ID")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FormularioResponseDTO> buscarFormulario(
            @PathVariable Long id) {

        return ResponseEntity.ok(formularioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar formulário")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FormularioResponseDTO> atualizarFormulario(
            @PathVariable Long id,
            @RequestBody FormularioRequestDTO dto) {

        return ResponseEntity.ok(formularioService.atualizarResposta(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover formulário")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarFormulario(
            @PathVariable Long id) {

        formularioService.deletarFormulario(id);
        return ResponseEntity.noContent().build();
    }
}