package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.repository;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.EstatisticaEvento;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEventoEstatistica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstatisticaEventoRepository extends JpaRepository<EstatisticaEvento, Long> {

    @Query("SELECT e FROM EstatisticaEvento e WHERE " +
            "(:noticiaId IS NULL OR e.noticiaId = :noticiaId) AND " +
            "(:usuarioId IS NULL OR LOWER(e.usuarioId) LIKE LOWER(CONCAT('%', :usuarioId, '%'))) AND " +
            "(:tipoEvento IS NULL OR e.tipoEvento = :tipoEvento)")
    Page<EstatisticaEvento> buscarComFiltros(
            @Param("noticiaId") Long noticiaId,
            @Param("usuarioId") String usuarioId,
            @Param("tipoEvento") TipoEventoEstatistica tipoEvento,
            Pageable pageable
    );

    long countByNoticiaId(Long noticiaId);

    long countByNoticiaIdAndTipoEvento(Long noticiaId, TipoEventoEstatistica tipoEvento);
}

