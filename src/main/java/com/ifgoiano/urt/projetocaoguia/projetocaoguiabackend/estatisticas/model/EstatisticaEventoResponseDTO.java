package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EstatisticaEventoResponseDTO {
    private Long id;
    private Long noticiaId;
    private String usuarioId;
    private TipoEventoEstatistica tipoEvento;
    private String origem;
    private LocalDateTime criadoEm;

    public static EstatisticaEventoResponseDTO from(EstatisticaEvento evento) {
        return EstatisticaEventoResponseDTO.builder()
                .id(evento.getId())
                .noticiaId(evento.getNoticiaId())
                .usuarioId(evento.getUsuarioId())
                .tipoEvento(evento.getTipoEvento())
                .origem(evento.getOrigem())
                .criadoEm(evento.getCriadoEm())
                .build();
    }
}

