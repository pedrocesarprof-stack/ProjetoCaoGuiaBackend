package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.repository;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.EstatisticaEvento;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEntidade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEventoEstatistica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstatisticaEventoRepository extends JpaRepository<EstatisticaEvento, Long> {

    @Query("SELECT e FROM EstatisticaEvento e WHERE " +
            "(:entidadeId IS NULL OR e.entidadeId = :entidadeId) AND " +
            "(:tipoEntidade IS NULL OR e.tipoEntidade = :tipoEntidade) AND " +
            "(:usuarioId IS NULL OR e.usuario.id = :usuarioId) AND " +
            "(:tipoEvento IS NULL OR e.tipoEvento = :tipoEvento)")
    Page<EstatisticaEvento> buscarComFiltros(
            @Param("entidadeId") Long entidadeId,
            @Param("tipoEntidade") TipoEntidade tipoEntidade,
            @Param("usuarioId") Long usuarioId,
            @Param("tipoEvento") TipoEventoEstatistica tipoEvento,
            Pageable pageable
    );

    long countByEntidadeIdAndTipoEntidade(Long entidadeId, TipoEntidade tipoEntidade);

    long countByEntidadeIdAndTipoEntidadeAndTipoEvento(Long entidadeId, TipoEntidade tipoEntidade, TipoEventoEstatistica tipoEvento);
}
