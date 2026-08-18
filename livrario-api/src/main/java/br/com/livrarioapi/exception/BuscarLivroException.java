package br.com.livrarioapi.exception;

public class BuscarLivroException extends RuntimeException {
    public BuscarLivroException(String message) {
        super(message);
    }
    public BuscarLivroException(String message, Throwable cause){
        super(message, cause);
    }
}
