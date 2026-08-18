package br.com.livrarioapi.service;

import br.com.livrarioapi.dto.LivroRequestDTO;
import br.com.livrarioapi.dto.LivroResponseDTO;
import br.com.livrarioapi.entity.Autor;
import br.com.livrarioapi.entity.Livro;
import br.com.livrarioapi.enums.Genero;
import br.com.livrarioapi.exception.CadastroLivroException;
import br.com.livrarioapi.repository.AutorRepository;
import br.com.livrarioapi.repository.LivroRepository;
import br.com.livrarioapi.utils.DocumentValidator;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CadastrarLivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public CadastrarLivroService(LivroRepository livroRepository, AutorRepository autorRepository){
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO livro){
        validarCampos(livro);
        Livro novoLivro = Livro.builder()
                .titulo(livro.titulo())
                .sinopse(livro.sinopse())
                .genero(Genero.valueOf(livro.genero()))
                .anoPublicacao(livro.anoPublicacao())
                .autores(livro.autores().stream().map(this::buscarOuCriarAutor).toList())
                .precoVenda(livro.precoVenda())
                .isbn(livro.isbn())
                .build();

        Livro livroCadastrado = livroRepository.save(novoLivro);

        return new LivroResponseDTO(livroCadastrado.getId(),
                livroCadastrado.getTitulo(),
                livroCadastrado.getSinopse(),
                livroCadastrado.getAnoPublicacao(),
                livroCadastrado.getPrecoVenda(),
                livroCadastrado.getIsbn(),
                String.valueOf(livroCadastrado.getGenero()),
                livroCadastrado.getAutores().stream().map(Autor::getNome).toList()
        );
    }

    private Autor buscarOuCriarAutor(String nome){
        return autorRepository.findByNome(nome).orElseGet(() -> autorRepository.save(Autor.builder().nome(nome).build()));
    }

    private void validarCampos(LivroRequestDTO livro){
        if(livro.titulo().isBlank()) throw new CadastroLivroException("Campo de título vazio");
        if(livro.genero().isBlank()) throw new CadastroLivroException("Campo de gênero literário vazio");
        if(livro.isbn().isBlank()) throw new CadastroLivroException("Campo de isbn vazio");
        if(livro.sinopse().isBlank()) throw new CadastroLivroException("Campo de sinopse vazio");
        if(livro.autores().isEmpty() || livro.autores().getFirst().isBlank()) throw new CadastroLivroException("Campo de autor(es) vazio");

        if(!DocumentValidator.isIsbnValido(livro.isbn())) throw new CadastroLivroException("ISBN inválido");
    }
}
