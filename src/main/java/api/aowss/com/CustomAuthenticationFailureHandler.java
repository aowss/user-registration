package api.aowss.com;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This handler is needed since the security framework intercepts the request before forwarding it to the MVC layer.
 * The MVC's ResponseEntityExceptionHandler is therefore never reached.
 * @see https://stackoverflow.com/a/41303283/6874527
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }

}