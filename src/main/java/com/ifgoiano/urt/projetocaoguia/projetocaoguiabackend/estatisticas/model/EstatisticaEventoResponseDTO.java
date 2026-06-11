package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioSimplificadoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EstatisticaEventoResponseDTO {
    private Long id;
    private Long entidadeId;
    private TipoEntidade tipoEntidade;
    private UsuarioSimplificadoDTO usuario;
    private TipoEventoEstatistica tipoEvento;
    private String origem;
    private LocalDateTime criadoEm;

    public static EstatisticaEventoResponseDTO from(EstatisticaEvento evento) {
        return EstatisticaEventoResponseDTO.builder()
                .id(evento.getId())
                .entidadeId(evento.getEntidadeId())
                .tipoEntidade(evento.getTipoEntidade())
                .usuario(UsuarioSimplificadoDTO.from(evento.getUsuario()))
                .tipoEvento(evento.getTipoEvento())
                .origem(evento.getOrigem())
                .criadoEm(evento.getCriadoEm())
                .build();
    }
}
