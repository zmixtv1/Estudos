package com.estudos.crud.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.crud.entities.Produto;
import com.estudos.crud.repositories.ProdutoRepository;
import com.estudos.crud.services.exceptions.RecursoNaoEncontradoException;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    // Injecao de dependencia pelo construtor (forma recomendada).
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Produto inserir(Produto produto) {
        produto.setId(null); // garante que sera um INSERT, e nao um UPDATE
        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto produto) {
        Produto existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id));

        existente.setNome(produto.getNome());
        existente.setDescricao(produto.getDescricao());
        existente.setPreco(produto.getPreco());
        existente.setQuantidade(produto.getQuantidade());

        return repository.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }
}
