package br.com.livrarioapi.repository;

import br.com.livrarioapi.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByLivrosTitulo(String titulo);
    Optional<Autor> findByNome(String nome);
}
