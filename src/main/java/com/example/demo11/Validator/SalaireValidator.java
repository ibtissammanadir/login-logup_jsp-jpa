package com.example.demo11.Validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("salaireValidator")
public class SalaireValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String salaireStr = (String) value;
        double salaire;
        try {
            salaire = Double.parseDouble(salaireStr);
        } catch (NumberFormatException e) {
            throw new ValidatorException(new FacesMessage("Erreur de validation: La valeur du salaire doit être un nombre valide."));
        }
        if (salaire <= 10000) {
            throw new ValidatorException(new FacesMessage("Erreur de validation: Le salaire doit être supérieur à 10000."));
        }
    }
}
