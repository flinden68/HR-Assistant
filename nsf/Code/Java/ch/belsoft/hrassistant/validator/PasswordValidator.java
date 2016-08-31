package ch.belsoft.hrassistant.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import ch.belsoft.hrassistant.controller.ApplicationController;

public class PasswordValidator implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public void validate(FacesContext context, UIComponent component, Object value)
    throws ValidatorException {
        if(!value.equals(ApplicationController.get().getUser().getPasswordGenerated())){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Filled in password doesn't match."));
        }
        
    }
    
}
