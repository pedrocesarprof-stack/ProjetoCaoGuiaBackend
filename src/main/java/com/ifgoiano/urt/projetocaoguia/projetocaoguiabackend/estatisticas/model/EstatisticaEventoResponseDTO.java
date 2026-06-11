package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EstatisticaEventoResponseDTO {
    private Long id;
    private Long entidadeId;
    private TipoEntidade tipoEntidade;
    private String usuarioId;
    private TipoEventoEstatistica tipoEvento;
    private String origem;
    private LocalDateTime criadoEm;

    public static EstatisticaEventoResponseDTO from(EstatisticaEvento evento) {
        return EstatisticaEventoResponseDTO.builder()
                .id(evento.getId())
                .entidadeId(evento.getEntidadeId())
                .tipoEntidade(evento.getTipoEntidade())
                .usuarioId(evento.getUsuarioId())
                .tipoEvento(evento.getTipoEvento())
                .origem(evento.getOrigem())
                .criadoEm(evento.getCriadoEm())
                .build();
    }
}
