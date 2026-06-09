package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.repository;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Long> {
}