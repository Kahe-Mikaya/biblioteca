package Municipal.biblioteca.infrastructure.jpa;

import Municipal.biblioteca.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface LivroJpaRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}