package api.aowss.com.resources;

import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.model.exceptions.UserNotFoundException;
import api.aowss.com.representations.Error;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

import static api.aowss.com.representations.Error.ErrorCode.EMAIL_ALREADY_EXISTS;
import static api.aowss.com.representations.Error.ErrorCode.INALID_FORMAT;
import static api.aowss.com.representations.Error.ErrorCode.USER_NOT_FOUND;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    @ResponseBody Error handleUserAlreadyExists(RuntimeException ex, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        //UserAlreadyExistsException exception = ex.getCause() == null ? (UserAlreadyExistsException)ex : (UserAlreadyExistsException)ex.getCause();
        details.put("email", ((UserAlreadyExistsException)ex.getCause()).getEmail());
        return new Error(EMAIL_ALREADY_EXISTS, details);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { UserNotFoundException.class })
    @ResponseBody Error handleUserNotFound(RuntimeException ex, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        UserNotFoundException exception = ex.getCause() == null ? (UserNotFoundException)ex : (UserNotFoundException)ex.getCause();
        details.put(exception.getIdType(), exception.getIdValue());
        return new Error(USER_NOT_FOUND, details);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseBody Error handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
        return new Error(null, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ValidationException.class })
    @ResponseBody Error handleValidationException(RuntimeException ex, WebRequest request) {
        return new Error(INALID_FORMAT, null);
    }

}
