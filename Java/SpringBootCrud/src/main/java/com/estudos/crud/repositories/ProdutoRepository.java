package com.estudos.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.crud.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // O Spring Data cria a consulta automaticamente a partir do nome do metodo.
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
