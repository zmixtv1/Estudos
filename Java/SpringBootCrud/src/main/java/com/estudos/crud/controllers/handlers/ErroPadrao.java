package com.estudos.crud.controllers.handlers;

import java.time.Instant;

public record ErroPadrao(Instant timestamp, Integer status, String erro, String mensagem, String caminho) {
}
