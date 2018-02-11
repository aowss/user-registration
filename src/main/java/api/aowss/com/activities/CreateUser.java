package api.aowss.com.activities;

import api.aowss.com.model.User;
import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.representations.UserSummary;
import api.aowss.com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@Component
public class CreateUser {

    private static final Logger logger = LoggerFactory.getLogger(CreateUser.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    UserService userService;

    public CompletableFuture<Void> createUser(UserSummary user) throws UserAlreadyExistsException {
        return userService.registerUser(new User(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword())));
    }

}
