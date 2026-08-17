package br.com.livrarioapi.service;

import br.com.livrarioapi.dto.UsuarioCreateDTO;
import br.com.livrarioapi.dto.UsuarioResponseDTO;
import br.com.livrarioapi.entity.Usuario;
import br.com.livrarioapi.exception.CadastroUsuarioException;
import br.com.livrarioapi.exception.LogarUsuarioException;
import br.com.livrarioapi.repository.UsuarioRepository;
import br.com.livrarioapi.utils.DocumentValidator;
import br.com.livrarioapi.utils.PasswordCryptUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogarUsuarioService {
    private final UsuarioRepository usuarioRepository;

    public LogarUsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO logarUsuario(String cpf, String senha){
        validarCampos(cpf, senha);

        Usuario usuarioCadastrado = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new LogarUsuarioException("Usuário de CPF " + cpf + " inexistente"));

        if(!PasswordCryptUtil.verificarSenha(senha, usuarioCadastrado.getSenha())) throw new LogarUsuarioException("Senha incorreta");

        return new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getNome());
    }

    private void validarCampos(String cpf, String senha) {
        if (cpf.isBlank()) throw new LogarUsuarioException("Campo de cpf vazio");
        if (senha.isBlank()) throw new LogarUsuarioException("Campo de senha vazio");

        if (!DocumentValidator.isCpfValido(cpf)) throw new LogarUsuarioException("CPF inválido");
    }
}
