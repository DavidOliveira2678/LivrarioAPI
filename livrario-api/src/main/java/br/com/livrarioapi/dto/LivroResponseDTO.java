package br.com.livrarioapi.dto;

import java.math.BigDecimal;
import java.util.List;

public record LivroResponseDTO(Long id,
                               String titulo,
                               String sinopse,
                               int anoPublicacao,
                               BigDecimal precoVenda,
                               String isbn,
                               String genero,
                               List<String> autores) {

}