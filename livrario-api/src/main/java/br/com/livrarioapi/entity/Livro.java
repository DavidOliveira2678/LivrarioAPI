package br.com.livrarioapi.entity;

import br.com.livrarioapi.enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String sinopse;
    private int anoPublicacao;
    private BigDecimal precoVenda;
    @Column(unique = true)
    private String isbn;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToMany
    @JoinTable(name="livro_has_autor",
            joinColumns = {@JoinColumn(name="livro_id")},
            inverseJoinColumns = {@JoinColumn(name="autor_id")})
    private List<Autor> autores;
}
