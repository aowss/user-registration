package api.aowss.com.resources;

import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.representations.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    @ResponseBody Error handleUserAlreadyExists(RuntimeException ex, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        details.put("email", ((UserAlreadyExistsException)ex).getEmail());
        return new Error("EMAIL_ALREADY_EXISTS", details);
    }

}
