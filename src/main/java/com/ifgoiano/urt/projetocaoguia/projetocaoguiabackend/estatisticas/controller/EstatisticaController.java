package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.EstatisticaEventoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.EstatisticaEventoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.EstatisticaResumoDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEventoEstatistica;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service.EstatisticaService;
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
@RequestMapping("/api/estatisticas")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticaService service;

    @PostMapping("/eventos")
    public ResponseEntity<EstatisticaEventoResponseDTO> registrarEvento(@Valid @RequestBody EstatisticaEventoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarEvento(dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<Page<EstatisticaEventoResponseDTO>> listarEventos(
            @RequestParam(required = false) Long noticiaId,
            @RequestParam(required = false) String usuarioId,
            @RequestParam(required = false) TipoEventoEstatistica tipoEvento,
            @PageableDefault(size = 10, sort = "criadoEm", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.listarEventos(noticiaId, usuarioId, tipoEvento, pageable));
    }

    @GetMapping("/noticias/{noticiaId}")
    public ResponseEntity<EstatisticaResumoDTO> resumoPorNoticia(@PathVariable Long noticiaId) {
        return ResponseEntity.ok(service.resumoPorNoticia(noticiaId));
    }
}

