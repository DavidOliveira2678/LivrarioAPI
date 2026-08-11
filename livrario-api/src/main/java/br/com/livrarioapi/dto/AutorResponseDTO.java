package br.com.livrarioapi.dto;

import java.util.List;

public record AutorResponseDTO(Long id, String nome, List<LivroResumoDTO> livros) {
}
