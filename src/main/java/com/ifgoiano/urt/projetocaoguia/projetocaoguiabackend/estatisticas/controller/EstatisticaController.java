package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.*;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service.EstatisticaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estatisticas")
@RequiredArgsConstructor
@Tag(
        name = "Estatísticas",
        description = "Gerenciamento das estatísticas e eventos do Projeto Cão-Guia Digital"
)
public class EstatisticaController {

    private final EstatisticaService service;

    @PostMapping("/eventos")
    @Operation(summary = "Registrar evento manualmente")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EstatisticaEventoResponseDTO> registrarEvento(
            @Valid @RequestBody EstatisticaEventoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarEvento(dto));
    }

    @GetMapping("/eventos")
    @Operation(
            summary = "Listar eventos",
            description = "Retorna uma lista paginada de eventos com filtros opcionais"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<EstatisticaEventoResponseDTO>> listarEventos(

            @Parameter(description = "Filtrar por ID da entidade")
            @RequestParam(required = false) Long entidadeId,

            @Parameter(description = "Filtrar por tipo de entidade (NOTICIA, DEPOIMENTO, TREINAMENTO, FORMULARIO, USUARIO)")
            @RequestParam(required = false) TipoEntidade tipoEntidade,

            @Parameter(description = "Filtrar por ID do usuário")
            @RequestParam(required = false) String usuarioId,

            @Parameter(description = "Filtrar por tipo de evento")
            @RequestParam(required = false) TipoEventoEstatistica tipoEvento,

            @PageableDefault(size = 10, sort = "criadoEm", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(service.listarEventos(entidadeId, tipoEntidade, usuarioId, tipoEvento, pageable));
    }

    @GetMapping("/{tipoEntidade}/{entidadeId}")
    @Operation(
            summary = "Resumo de estatísticas por entidade",
            description = "Retorna o resumo de todos os eventos registrados para uma entidade específica"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstatisticaResumoDTO> resumoPorEntidade(
            @Parameter(description = "Tipo da entidade (NOTICIA, DEPOIMENTO, TREINAMENTO, FORMULARIO, USUARIO)")
            @PathVariable TipoEntidade tipoEntidade,

            @Parameter(description = "ID da entidade")
            @PathVariable Long entidadeId) {

        return ResponseEntity.ok(service.resumoPorEntidade(entidadeId, tipoEntidade));
    }
}
