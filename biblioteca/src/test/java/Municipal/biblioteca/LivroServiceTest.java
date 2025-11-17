package Municipal.biblioteca;

import Municipal.biblioteca.application.service.LivroService;
import Municipal.biblioteca.domain.exception.BusinessException;
import Municipal.biblioteca.domain.exception.NotFoundException;
import Municipal.biblioteca.domain.model.Livro;
import Municipal.biblioteca.domain.port.LivroRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepositoryPort repository;

    @InjectMocks
    private LivroService service;

    // Remove o @BeforeEach pois não é necessário com @ExtendWith

    @Test
    void criar_deveSalvarLivro_quandoIsbnNaoExistir() {
        Livro livro = new Livro("Diario de um banana", "nao sei mas parece gente boa", "123", 2020, 5);

        when(repository.existsByIsbn("123")).thenReturn(false);
        when(repository.save(any(Livro.class))).thenAnswer(i -> {
            Livro l = i.getArgument(0);
            l.setId(1L);
            return l;
        });

        Livro criado = service.criar(livro);

        assertNotNull(criado.getId());
        assertEquals("123", criado.getIsbn());
        verify(repository).save(livro); // Verifica se o save foi chamado
    }

    @Test
    void atualizar_deveAtualizar_quandoValido() {
        Livro existente = new Livro("diaro de um banana 2","nao sei mas parece gente boa","1",2020,1);
        existente.setId(1L);

        Livro dados = new Livro("diaro de um banana : rodric é o cara","enzo","1",2022,3);

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        Livro atualizado = service.atualizar(1L, dados);

        assertEquals("diaro de um banana : rodric é o cara", atualizado.getTitulo());
        assertEquals("enzo", atualizado.getAutor());
    }

    @Test
    void atualizar_deveLancarErro_quandoIsbnJaExisteEmOutro() {
        Livro existente = new Livro("Old","OldA","I1",2000,1);
        existente.setId(1L);

        Livro dados = new Livro("New","NewA","I2",2022,3);

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.existsByIsbn("I2")).thenReturn(true);

        assertThrows(BusinessException.class, () -> service.atualizar(1L, dados));
    }

    @Test
    void remover_deveRemover_quandoExistir() {
        Livro existente = new Livro();
        existente.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.remover(1L));
        verify(repository).deleteById(1L);
    }

    @Test
    void remover_deveLancarNotFound_quandoNaoExistir() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.remover(2L));
    }
}
