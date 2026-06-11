package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.service.DepoimentoService;

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
@RequestMapping("/api/depoimentos")
@RequiredArgsConstructor
@Tag(
        name = "Depoimentos",
        description = "Gerenciamento dos depoimentos dos usuários"
)
public class DepoimentoController {

    private final DepoimentoService service;

    @PostMapping
    @Operation(summary = "Cadastrar depoimento")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DepoimentoResponseDTO> criar(
            @Valid @RequestBody DepoimentoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    @Operation(
            summary = "Listar depoimentos",
            description = "Retorna uma lista paginada de depoimentos cadastrados"
    )
    public ResponseEntity<Page<DepoimentoResponseDTO>> listar(

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
    @Operation(summary = "Buscar depoimento por ID")
    public ResponseEntity<DepoimentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar depoimento")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DepoimentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DepoimentoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover depoimento")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> remover(
            @PathVariable Long id) {

        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}