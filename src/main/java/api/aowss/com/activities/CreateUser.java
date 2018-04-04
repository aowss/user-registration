package api.aowss.com.activities;

import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.representations.UserSummary;
import api.aowss.com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

import static api.aowss.com.activities.FromRepresentation.toUser;

@Component
public class CreateUser {

    private static final Logger logger = LoggerFactory.getLogger(CreateUser.class);

    @Inject
    UserService userService;

    public CompletableFuture<Long> createUser(UserSummary user) throws UserAlreadyExistsException {
        return userService.registerUser(toUser.apply(user));
    }

}
