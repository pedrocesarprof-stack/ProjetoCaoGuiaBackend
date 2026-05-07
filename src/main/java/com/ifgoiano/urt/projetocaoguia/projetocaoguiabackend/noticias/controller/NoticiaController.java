package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.model.*;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.service.NoticiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaService service;

    @PostMapping
    public ResponseEntity<NoticiaResponseDTO> criar(@Valid @RequestBody NoticiaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticiaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticiaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody NoticiaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<NoticiaResponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam StatusNoticia status) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<NoticiaResponseDTO>> listar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) CategoriaNoticia categoria,
            @RequestParam(required = false) StatusNoticia status,
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @PageableDefault(size = 10, sort = "criadoEm", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.listar(titulo, autor, categoria, status, tags, dataInicio, dataFim, pageable));
    }

}

