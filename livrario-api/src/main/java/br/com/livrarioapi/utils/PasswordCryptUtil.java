package br.com.livrarioapi.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordCryptUtil {

    public static String hashearSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }

    public static boolean verificarSenha(String senha, String senhaHasheada){
        return BCrypt.checkpw(senha, senhaHasheada);
    }
}
