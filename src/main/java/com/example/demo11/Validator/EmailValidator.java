package com.example.demo11.Validator;

import com.example.demo11.dao.DaoImpl;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("uniqueEmailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        DaoImpl daoimpl = new DaoImpl();
        if (!email.contains("@")) {
            throw new ValidatorException(new FacesMessage("Erreur de validation ,L'e-mail doit contenir '@'"));
        }
        if ( daoimpl.isEmailExists(email)) {
            throw new ValidatorException(new FacesMessage("Email existe , veuillez saisir un nouvel email."));
        }
    }
}