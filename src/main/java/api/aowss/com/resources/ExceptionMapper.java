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

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    @ResponseBody
    Error handleNotFound(RuntimeException ex, WebRequest request) {
        return new Error("code", "desc");
    }

}
