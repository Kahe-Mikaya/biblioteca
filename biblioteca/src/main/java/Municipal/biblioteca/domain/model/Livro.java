package Municipal.biblioteca.domain.model;
import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "livros", uniqueConstraints = {@UniqueConstraint(columnNames = "isbn")})
public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String titulo;


    @Column(nullable = false)
    private String autor;


    @Column(nullable = false, unique = true)
    private String isbn;


    private Integer anoPublicacao;


    private Integer quantidadeEstoque;


    // construtores
    public Livro() {}


    public Livro(String titulo, String autor, String isbn, Integer anoPublicacao, Integer quantidadeEstoque) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeEstoque = quantidadeEstoque;
    }


    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id) && Objects.equals(isbn, livro.isbn);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
}