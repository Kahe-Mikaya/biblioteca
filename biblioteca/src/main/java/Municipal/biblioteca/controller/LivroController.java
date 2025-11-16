package Municipal.biblioteca.controller;

import Municipal.biblioteca.application.service.LivroService;
import Municipal.biblioteca.domain.exception.BusinessException;
import Municipal.biblioteca.domain.exception.NotFoundException;
import Municipal.biblioteca.domain.model.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/api/livros")
public class LivroController {


    private final LivroService service;


    public LivroController(LivroService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Livro livro) {
        try {
            Livro criado = service.criar(livro);
            return new ResponseEntity<>(criado, HttpStatus.CREATED);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping
    public List<Livro> listar() {
        return service.listarTodos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/by-isbn/{isbn}")
    public ResponseEntity<?> buscarPorIsbn(@PathVariable String isbn) {
        try {
            return ResponseEntity.ok(service.buscarPorIsbn(isbn));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        try {
            return ResponseEntity.ok(service.atualizar(id, livro));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}