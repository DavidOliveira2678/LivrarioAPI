package br.com.livrarioapi.exception;

public class DeletarLivroException extends RuntimeException {
    public DeletarLivroException(String message) {
        super(message);
    }
    public DeletarLivroException(String message, Throwable cause){
        super(message, cause);
    }
}
