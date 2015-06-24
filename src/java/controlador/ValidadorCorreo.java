/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bryanmq
 */
@FacesValidator("validadorCorreo")
public class ValidadorCorreo implements Validator {

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final static Pattern EMAIL_COMPILED_PATTERN = Pattern.compile(EMAIL_PATTERN);

    public ValidadorCorreo() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object o) throws ValidatorException {
        if (o == null || "".equals((String) o)) {
            FacesMessage msg = new FacesMessage("Escribir el correo", "Escribe una dirección de correo electrónico con el formato:\nalguien@example.com.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

        Matcher matcher = EMAIL_COMPILED_PATTERN.matcher((String) o);

        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Correo inválido", "Escribe una dirección de correo electrónico con el formato:\nalguien@example.com");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
