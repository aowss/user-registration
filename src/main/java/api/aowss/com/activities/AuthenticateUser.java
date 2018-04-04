package api.aowss.com.activities;

import api.aowss.com.model.AccountStatus;
import api.aowss.com.model.exceptions.InvalidStatusException;
import api.aowss.com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@Component
public class AuthenticateUser {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateUser.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    UserService userService;

    public CompletableFuture<Boolean> authenticateUser(String email, String password) {
        return userService.retrieveUserByEmail(email).thenApply(user -> {
            if (!user.getStatus().equals(AccountStatus.ACTIVE)) throw new InvalidStatusException(user.getStatus());
            if (passwordEncoder.matches(password, user.getPassword())) return true;
            return false;
        });
    }

}
