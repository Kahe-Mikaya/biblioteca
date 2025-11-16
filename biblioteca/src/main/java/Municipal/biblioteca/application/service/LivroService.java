package Municipal.biblioteca.application.service;

import Municipal.biblioteca.domain.exception.BusinessException;
import Municipal.biblioteca.domain.exception.NotFoundException;
import Municipal.biblioteca.domain.model.Livro;
import Municipal.biblioteca.domain.port.LivroRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class LivroService {


    private final LivroRepositoryPort repository;


    public LivroService(LivroRepositoryPort repository) {
        this.repository = repository;
    }


    @Transactional
    public Livro criar(Livro livro) {
        if (repository.existsByIsbn(livro.getIsbn())) {
            throw new BusinessException("ISBN já cadastrado: " + livro.getIsbn());
        }
        return repository.save(livro);
    }


    @Transactional(readOnly = true)
    public List<Livro> listarTodos() {
        return repository.findAll();
    }


    @Transactional(readOnly = true)
    public Livro buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Livro não encontrado com id: " + id));
    }


    @Transactional(readOnly = true)
    public Livro buscarPorIsbn(String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(() -> new NotFoundException("Livro não encontrado com ISBN: " + isbn));
    }


    @Transactional
    public Livro atualizar(Long id, Livro dados) {
        Livro existente = repository.findById(id).orElseThrow(() -> new NotFoundException("Livro não encontrado com id: " + id));


        if (!existente.getIsbn().equals(dados.getIsbn()) && repository.existsByIsbn(dados.getIsbn())) {
            throw new BusinessException("ISBN já cadastrado por outro livro: " + dados.getIsbn());
        }


        existente.setTitulo(dados.getTitulo());
        existente.setAutor(dados.getAutor());
        existente.setIsbn(dados.getIsbn());
        existente.setAnoPublicacao(dados.getAnoPublicacao());
        existente.setQuantidadeEstoque(dados.getQuantidadeEstoque());


        return repository.save(existente);
    }


    @Transactional
    public void remover(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Livro não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}