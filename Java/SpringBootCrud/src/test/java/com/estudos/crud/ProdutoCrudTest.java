package com.estudos.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.estudos.crud.entities.Produto;
import com.estudos.crud.services.ProdutoService;
import com.estudos.crud.services.exceptions.RecursoNaoEncontradoException;

@SpringBootTest
class ProdutoCrudTest {

    @Autowired
    private ProdutoService service;

    @Test
    void deveCarregarOsProdutosDoDataSql() {
        assertFalse(service.buscarTodos().isEmpty());
    }

    @Test
    void deveInserirAtualizarEDeletar() {
        Produto novo = service.inserir(new Produto(null, "Monitor", "Monitor 27 polegadas", 1800.0, 5));
        assertTrue(novo.getId() != null);

        novo.setPreco(1650.0);
        Produto atualizado = service.atualizar(novo.getId(), novo);
        assertEquals(1650.0, atualizado.getPreco());

        service.deletar(novo.getId());
        assertThrows(RecursoNaoEncontradoException.class, () -> service.buscarPorId(novo.getId()));
    }

    @Test
    void deveLancarExcecaoQuandoOIdNaoExiste() {
        assertThrows(RecursoNaoEncontradoException.class, () -> service.buscarPorId(9999L));
    }
}
