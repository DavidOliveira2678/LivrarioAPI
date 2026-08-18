package br.com.livrarioapi.utils;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.constraints.ISBN;

import java.util.Set;

public class DocumentValidator {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static boolean isCpfValido(String cpf){
        try{
            CPFValidator validator = new CPFValidator();
            validator.assertValid(cpf);
            return true;

        } catch(InvalidStateException e){
            return false;
        }
    }

    public static boolean isIsbnValido(String isbn){
        Set<ConstraintViolation<IsbnHolder>> violations = VALIDATOR.validate(new IsbnHolder(isbn));

        return violations.isEmpty();
    }

    private static class IsbnHolder{
        @ISBN
        String value;

        IsbnHolder(String value){
            this.value = value;
        }
    }
}
