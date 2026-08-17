package br.com.livrarioapi.utils;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import java.util.List;

public class DocumentValidator {
    public static boolean isCpfValido(String cpf){
        try{
            CPFValidator validator = new CPFValidator();
            validator.assertValid(cpf);
            return true;

        } catch(InvalidStateException e){
            return false;
        }
    }

}
