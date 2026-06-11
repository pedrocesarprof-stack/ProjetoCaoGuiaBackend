package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.security.AuthenticationFacade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.*;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.repository.EstatisticaEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    private final EstatisticaEventoRepository repository;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public EstatisticaEventoResponseDTO registrarEvento(EstatisticaEventoRequestDTO dto) {
        var usuario = authenticationFacade.getAuthenticatedUserOrNull();

        var evento = EstatisticaEvento.builder()
                .entidadeId(dto.getEntidadeId())
                .tipoEntidade(dto.getTipoEntidade())
                .usuario(usuario)
                .tipoEvento(dto.getTipoEvento())
                .origem(dto.getOrigem())
                .build();

        return EstatisticaEventoResponseDTO.from(repository.save(evento));
    }

    @Transactional
    public void registrarEventoInterno(Long entidadeId, TipoEntidade tipoEntidade, TipoEventoEstatistica tipoEvento) {
        var usuario = authenticationFacade.getAuthenticatedUserOrNull();

        var evento = EstatisticaEvento.builder()
                .entidadeId(entidadeId)
                .tipoEntidade(tipoEntidade)
                .usuario(usuario)
                .tipoEvento(tipoEvento)
                .origem("interno")
                .build();

        repository.save(evento);
    }

    public Page<EstatisticaEventoResponseDTO> listarEventos(
            Long entidadeId,
            TipoEntidade tipoEntidade,
            Long usuarioId,
            TipoEventoEstatistica tipoEvento,
            Pageable pageable) {
        return repository.buscarComFiltros(entidadeId, tipoEntidade, usuarioId, tipoEvento, pageable)
                .map(EstatisticaEventoResponseDTO::from);
    }

    public EstatisticaResumoDTO resumoPorEntidade(Long entidadeId, TipoEntidade tipoEntidade) {
        return EstatisticaResumoDTO.builder()
                .entidadeId(entidadeId)
                .tipoEntidade(tipoEntidade)
                .totalEventos(repository.countByEntidadeIdAndTipoEntidade(entidadeId, tipoEntidade))
                .totalVisualizacoes(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.VISUALIZACAO))
                .totalLikes(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.LIKE))
                .totalDeslikes(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.DESLIKE))
                .totalCompartilhamentos(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.COMPARTILHAMENTO))
                .totalComentarios(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.COMENTARIO))
                .totalCriacoes(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.CRIACAO))
                .totalAtualizacoes(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.ATUALIZACAO))
                .totalExclusoes(repository.countByEntidadeIdAndTipoEntidadeAndTipoEvento(entidadeId, tipoEntidade, TipoEventoEstatistica.EXCLUSAO))
                .build();
    }
}
