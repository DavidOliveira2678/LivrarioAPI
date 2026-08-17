package br.com.livrarioapi.exception;

public class LogarUsuarioException extends RuntimeException {
    public LogarUsuarioException(String message) {
        super(message);
    }

    public LogarUsuarioException(String message, Throwable cause){
        super(message, cause);
    }
}
