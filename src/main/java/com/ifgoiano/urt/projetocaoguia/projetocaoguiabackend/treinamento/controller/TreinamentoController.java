package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto.TreinamentoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto.TreinamentoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.service.TreinamentoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treinamentos")
@RequiredArgsConstructor
public class TreinamentoController {

    private final TreinamentoService service;

    @PostMapping
    public ResponseEntity<TreinamentoResponseDTO> criar(
            @Valid @RequestBody TreinamentoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<TreinamentoResponseDTO>> listar(
            @PageableDefault(size = 10,
                    sort = "id",
                    direction = Sort.Direction.ASC)
            Pageable pageable) {

        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinamentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TreinamentoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id) {

        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}