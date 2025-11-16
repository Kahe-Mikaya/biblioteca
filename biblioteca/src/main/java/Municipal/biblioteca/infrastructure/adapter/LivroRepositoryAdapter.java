package Municipal.biblioteca.infrastructure.adapter;

import Municipal.biblioteca.domain.model.Livro;
import Municipal.biblioteca.domain.port.LivroRepositoryPort;
import Municipal.biblioteca.infrastructure.jpa.LivroJpaRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


@Component
public class LivroRepositoryAdapter implements LivroRepositoryPort {


    private final LivroJpaRepository jpa;


    public LivroRepositoryAdapter(LivroJpaRepository jpa) {
        this.jpa = jpa;
    }


    @Override
    public Livro save(Livro livro) {
        return jpa.save(livro);
    }


    @Override
    public List<Livro> findAll() {
        return jpa.findAll();
    }


    @Override
    public Optional<Livro> findById(Long id) {
        return jpa.findById(id);
    }


    @Override
    public Optional<Livro> findByIsbn(String isbn) {
        return jpa.findByIsbn(isbn);
    }


    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }


    @Override
    public boolean existsByIsbn(String isbn) {
        return jpa.existsByIsbn(isbn);
    }
}