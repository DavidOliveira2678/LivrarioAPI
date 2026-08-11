package br.com.livrarioapi.repository;

import br.com.livrarioapi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByAutoresNome(String nome);

    List<Livro> findByTitulo(String titulo);
}
