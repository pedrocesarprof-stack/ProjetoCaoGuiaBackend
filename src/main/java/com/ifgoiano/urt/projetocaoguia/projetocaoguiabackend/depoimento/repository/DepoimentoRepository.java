package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.repository;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.model.Depoimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {

    @Query("""
            SELECT d FROM Depoimento d
            WHERE (:descricao IS NULL OR LOWER(d.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')))
           """)
    Page<Depoimento> buscarComFiltros(
            @Param("descricao") String descricao,
            Pageable pageable
    );
}