package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.NoticiaNotFoundException;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.*;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.repository.EstatisticaEventoRepository;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.repository.NoticiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    private final EstatisticaEventoRepository repository;
    private final NoticiaRepository noticiaRepository;

    @Transactional
    public EstatisticaEventoResponseDTO registrarEvento(EstatisticaEventoRequestDTO dto) {
        validarNoticia(dto.getNoticiaId());

        var evento = EstatisticaEvento.builder()
                .noticiaId(dto.getNoticiaId())
                .usuarioId(dto.getUsuarioId())
                .tipoEvento(dto.getTipoEvento())
                .origem(dto.getOrigem())
                .build();

        return EstatisticaEventoResponseDTO.from(repository.save(evento));
    }

    public Page<EstatisticaEventoResponseDTO> listarEventos(
            Long noticiaId,
            String usuarioId,
            TipoEventoEstatistica tipoEvento,
            Pageable pageable) {
        return repository.buscarComFiltros(noticiaId, usuarioId, tipoEvento, pageable)
                .map(EstatisticaEventoResponseDTO::from);
    }

    public EstatisticaResumoDTO resumoPorNoticia(Long noticiaId) {
        validarNoticia(noticiaId);

        return EstatisticaResumoDTO.builder()
                .noticiaId(noticiaId)
                .totalEventos(repository.countByNoticiaId(noticiaId))
                .totalVisualizacoes(repository.countByNoticiaIdAndTipoEvento(noticiaId, TipoEventoEstatistica.VISUALIZACAO))
                .totalLikes(repository.countByNoticiaIdAndTipoEvento(noticiaId, TipoEventoEstatistica.LIKE))
                .totalDeslikes(repository.countByNoticiaIdAndTipoEvento(noticiaId, TipoEventoEstatistica.DESLIKE))
                .totalCompartilhamentos(repository.countByNoticiaIdAndTipoEvento(noticiaId, TipoEventoEstatistica.COMPARTILHAMENTO))
                .totalComentarios(repository.countByNoticiaIdAndTipoEvento(noticiaId, TipoEventoEstatistica.COMENTARIO))
                .build();
    }

    private void validarNoticia(Long noticiaId) {
        if (!noticiaRepository.existsById(noticiaId)) {
            throw new NoticiaNotFoundException(noticiaId);
        }
    }
}

