package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core;

public class NoticiaNotFoundException extends RuntimeException {
    public NoticiaNotFoundException(Long id) {
        super("Notícia não encontrada com id: " + id);
    }
}

