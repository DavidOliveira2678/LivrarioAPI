package br.com.livrarioapi.controller;

import br.com.livrarioapi.dto.LivroRequestDTO;
import br.com.livrarioapi.dto.LivroResponseDTO;
import br.com.livrarioapi.service.BuscarLivrosService;
import br.com.livrarioapi.service.CadastrarLivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    private final CadastrarLivroService cadastrarLivroService;
    private final BuscarLivrosService buscarLivrosService;

    public LivroController(CadastrarLivroService cadastrarLivroService,
                           BuscarLivrosService buscarLivrosService){

        this.cadastrarLivroService = cadastrarLivroService;
        this.buscarLivrosService = buscarLivrosService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<LivroResponseDTO> cadastrarLivro(@RequestBody LivroRequestDTO livro){
        LivroResponseDTO livroRegistrado = this.cadastrarLivroService.cadastrarLivro(livro);
        return ResponseEntity.created(URI.create("/livro/" + livroRegistrado.id())).body(livroRegistrado);
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<LivroResponseDTO>> buscarLivros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String isbn){

        if(titulo != null && !titulo.isEmpty()) return ResponseEntity.ok().body(this.buscarLivrosService.buscarPorTitulo(titulo));
        if(autor != null && !autor.isEmpty()) return ResponseEntity.ok().body(this.buscarLivrosService.buscarPorAutor(autor));
        if(isbn != null && !isbn.isEmpty()) return ResponseEntity.ok().body(this.buscarLivrosService.buscarPorIsbn(isbn));

        return ResponseEntity.ok().body(buscarLivrosService.buscarTodos());
    }
}
