package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core;

/**
 * Exceção genérica para entidades não encontradas
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s não encontrado(a) com ID: %d", entityName, id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}

