package br.com.livrarioapi.service;

import br.com.livrarioapi.dto.LivroRequestDTO;
import br.com.livrarioapi.dto.LivroResponseDTO;
import br.com.livrarioapi.entity.Autor;
import br.com.livrarioapi.entity.Livro;
import br.com.livrarioapi.exception.DeletarLivroException;
import br.com.livrarioapi.repository.AutorRepository;
import br.com.livrarioapi.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeletarLivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public DeletarLivroService(LivroRepository livroRepository, AutorRepository autorRepository){
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public boolean excluirLivro(String isbn){
        Optional<Livro> livroOpt = livroRepository.findByIsbn(isbn);
        if(livroOpt.isEmpty()) throw new DeletarLivroException("Livro inexistente");

        Livro livroRegistrado = livroOpt.get();
        List<Autor> autores = livroRegistrado.getAutores();

        livroRepository.delete(livroRegistrado);

        for(Autor autor : autores){
            if(!autorRepository.existsByIdAndLivrosIsNotEmpty(autor.getId())) autorRepository.delete(autor);
        }

        return true;
    }
}
