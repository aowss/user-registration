package api.aowss.com;

import api.aowss.com.activities.AuthenticateUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Inject
    AuthenticateUser authenticateUserActivity;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        boolean result = authenticateUserActivity.authenticateUser(name, password).
            //  TODO: deal with different type of exceptions ( InvalidStatusException ) and return a different message
            exceptionally(exception -> false).
            join();

        if (result) {
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        } else {
            throw new BadCredentialsException("invalid credentials");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}