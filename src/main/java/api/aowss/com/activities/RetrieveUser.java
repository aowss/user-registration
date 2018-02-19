package api.aowss.com.activities;

import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.model.exceptions.UserNotFoundException;
import api.aowss.com.representations.UserRepresentation;
import api.aowss.com.representations.UserSummary;
import api.aowss.com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

import static api.aowss.com.activities.FromModel.toUserRepresentation;

@Component
public class RetrieveUser {

    private static final Logger logger = LoggerFactory.getLogger(RetrieveUser.class);

    @Inject
    UserService userService;

    public CompletableFuture<UserRepresentation> retrieveUser(Long userId) throws UserNotFoundException {
        return userService.retrieveUser(userId).thenApply(toUserRepresentation);
    }

}
