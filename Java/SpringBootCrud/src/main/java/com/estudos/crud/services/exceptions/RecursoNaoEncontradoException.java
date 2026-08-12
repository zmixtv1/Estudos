package com.estudos.crud.services.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(Object id) {
        super("Produto nao encontrado. Id: " + id);
    }
}
