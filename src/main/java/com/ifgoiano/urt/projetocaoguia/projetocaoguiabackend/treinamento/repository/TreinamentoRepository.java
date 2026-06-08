package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.repository;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {
}