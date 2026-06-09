package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.service.DepoimentoService;

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
@RequestMapping("/api/depoimentos")
@RequiredArgsConstructor
public class DepoimentoController {

    private final DepoimentoService service;

    @PostMapping
    public ResponseEntity<DepoimentoResponseDTO> criar(
            @Valid @RequestBody DepoimentoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<DepoimentoResponseDTO>> listar(
            @PageableDefault(
                    size = 10,
                    sort = "id",
                    direction = Sort.Direction.ASC)
            Pageable pageable) {

        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepoimentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepoimentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DepoimentoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id) {

        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}