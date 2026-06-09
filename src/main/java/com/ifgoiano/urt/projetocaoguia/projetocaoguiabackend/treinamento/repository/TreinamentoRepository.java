package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.repository;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.model.Treinamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {

    @Query("""
            SELECT t FROM Treinamento t
            WHERE (:titulo IS NULL OR LOWER(t.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
            AND (:etapa IS NULL OR LOWER(t.etapa) LIKE LOWER(CONCAT('%', :etapa, '%')))
           """)
    Page<Treinamento> buscarComFiltros(
            @Param("titulo") String titulo,
            @Param("etapa") String etapa,
            Pageable pageable
    );
}