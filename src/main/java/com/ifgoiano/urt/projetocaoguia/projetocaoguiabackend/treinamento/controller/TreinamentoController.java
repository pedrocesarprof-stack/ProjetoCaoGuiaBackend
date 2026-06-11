package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto.TreinamentoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto.TreinamentoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.service.TreinamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treinamentos")
@RequiredArgsConstructor
@Tag(
        name = "Treinamentos",
        description = "Gerenciamento dos treinamentos do Projeto Cão-Guia Digital"
)
public class TreinamentoController {

    private final TreinamentoService service;

    @PostMapping
    @Operation(summary = "Cadastrar treinamento")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TreinamentoResponseDTO> criar(
            @Valid @RequestBody TreinamentoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    @Operation(
            summary = "Listar treinamentos",
            description = "Retorna uma lista paginada de treinamentos cadastrados"
    )
    public ResponseEntity<Page<TreinamentoResponseDTO>> listar(

            @Parameter(description = "Número da página")
            @RequestParam(defaultValue = "0")
            int page,

            @Parameter(description = "Quantidade de registros por página")
            @RequestParam(defaultValue = "10")
            int size,

            @Parameter(description = "Campo utilizado para ordenação")
            @RequestParam(defaultValue = "id")
            String sort,

            @Parameter(description = "Direção da ordenação (ASC ou DESC)")
            @RequestParam(defaultValue = "ASC")
            String direction) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.fromString(direction),
                sort
        );

        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar treinamento por ID")
    public ResponseEntity<TreinamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar treinamento")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TreinamentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TreinamentoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover treinamento")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> remover(
            @PathVariable Long id) {

        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}