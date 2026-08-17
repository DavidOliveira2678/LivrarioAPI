package br.com.livrarioapi.exception;

public class CadastroUsuarioException extends RuntimeException {
    public CadastroUsuarioException(String message) {
        super(message);
    }
}
