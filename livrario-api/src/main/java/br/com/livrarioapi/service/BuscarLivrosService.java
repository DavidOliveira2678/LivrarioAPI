package br.com.livrarioapi.service;

import br.com.livrarioapi.dto.LivroResponseDTO;
import br.com.livrarioapi.entity.Autor;
import br.com.livrarioapi.entity.Livro;
import br.com.livrarioapi.exception.BuscarLivroException;
import br.com.livrarioapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarLivrosService {
    private final LivroRepository livroRepository;

    public BuscarLivrosService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<LivroResponseDTO> buscarTodos(){
        return livroRepository.findAll().stream().map(this::mapearLivro).toList();
    }

    public List<LivroResponseDTO> buscarPorAutor(String nomeAutor){
        return livroRepository.findByAutoresNome(nomeAutor).stream().map(this::mapearLivro).toList();
    }

    public List<LivroResponseDTO> buscarPorTitulo(String titulo){
        return livroRepository.findByTitulo(titulo).stream().map(this::mapearLivro).toList();
    }

    public LivroResponseDTO buscarPorIsbn(String isbn){
        return livroRepository.findByIsbn(isbn).map(this::mapearLivro).orElseThrow(() -> new BuscarLivroException("Livro de ISBN " + isbn + " não encontrado"));
    }

    private LivroResponseDTO mapearLivro(Livro livro){
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getSinopse(),
                livro.getAnoPublicacao(),
                livro.getPrecoVenda(),
                livro.getIsbn(),
                String.valueOf(livro.getGenero()),
                livro.getAutores().stream().map(Autor::getNome).toList());
    }
}
