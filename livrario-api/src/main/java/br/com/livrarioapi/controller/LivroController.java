package br.com.livrarioapi.controller;

import br.com.livrarioapi.dto.LivroRequestDTO;
import br.com.livrarioapi.dto.LivroResponseDTO;
import br.com.livrarioapi.service.BuscarLivrosService;
import br.com.livrarioapi.service.CadastrarLivroService;
import br.com.livrarioapi.service.DeletarLivroService;
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
    private final DeletarLivroService deletarLivroService;

    public LivroController(CadastrarLivroService cadastrarLivroService, BuscarLivrosService buscarLivrosService, DeletarLivroService deletarLivroService){
        this.cadastrarLivroService = cadastrarLivroService;
        this.buscarLivrosService = buscarLivrosService;
        this.deletarLivroService = deletarLivroService;
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

    @DeleteMapping(value = "/deletar/{isbn}")
    public ResponseEntity<Void> deletarLivro(@PathVariable String isbn){
        this.deletarLivroService.excluirLivro(isbn);
        return ResponseEntity.noContent().build();
    }

}
