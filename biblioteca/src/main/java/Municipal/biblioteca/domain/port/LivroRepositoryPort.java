package Municipal.biblioteca.domain.port;

import Municipal.biblioteca.domain.model.Livro;

import java.util.List;
import java.util.Optional;


public interface LivroRepositoryPort {
    Livro save(Livro livro);
    List<Livro> findAll();
    Optional<Livro> findById(Long id);
    Optional<Livro> findByIsbn(String isbn);
    void deleteById(Long id);
    boolean existsByIsbn(String isbn);
}