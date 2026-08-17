package br.com.livrarioapi.service;

import br.com.livrarioapi.dto.UsuarioCreateDTO;
import br.com.livrarioapi.dto.UsuarioResponseDTO;
import br.com.livrarioapi.entity.Usuario;
import br.com.livrarioapi.exception.CadastroUsuarioException;
import br.com.livrarioapi.repository.UsuarioRepository;
import br.com.livrarioapi.utils.DocumentValidator;
import br.com.livrarioapi.utils.PasswordCryptUtil;
import org.springframework.stereotype.Service;

@Service
public class CadastrarFuncionarioService {
    private final UsuarioRepository usuarioRepository;

    public CadastrarFuncionarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        validarCampos(usuarioCreateDTO);
        String senhaHash = PasswordCryptUtil.hashearSenha(usuarioCreateDTO.senha());

        Usuario usuario = Usuario.builder()
                .nome(usuarioCreateDTO.nome())
                .cpf(usuarioCreateDTO.cpf())
                .senha(senhaHash)
                .build();

        Usuario usuarioCadastrado = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getNome());
    }

    private void validarCampos(UsuarioCreateDTO usuario) {
        if (usuario.nome().isBlank()) throw new CadastroUsuarioException("Campo de nome vazio");
        if (usuario.cpf().isBlank()) throw new CadastroUsuarioException("Campo de cpf vazio");
        if (usuario.senha().isBlank()) throw new CadastroUsuarioException("Campo de senha vazio");

        if (!DocumentValidator.isCpfValido(usuario.cpf())) throw new CadastroUsuarioException("CPF inválido");
    }
}
