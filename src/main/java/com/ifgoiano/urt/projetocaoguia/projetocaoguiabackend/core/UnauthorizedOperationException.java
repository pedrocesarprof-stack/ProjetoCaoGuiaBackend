package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core;

/**
 * Exceção para operações não autorizadas
 */
public class UnauthorizedOperationException extends RuntimeException {

    public UnauthorizedOperationException(String message) {
        super(message);
    }

    public UnauthorizedOperationException() {
        super("Você não tem permissão para realizar esta operação");
    }
}

