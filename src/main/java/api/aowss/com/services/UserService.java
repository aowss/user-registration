package api.aowss.com.services;

import api.aowss.com.model.User;
import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.model.exceptions.UserNotFoundException;

import java.util.concurrent.CompletableFuture;

public interface UserService {

    CompletableFuture<Long> registerUser(User userSummary) throws UserAlreadyExistsException;
    CompletableFuture<User> retrieveUser(String userId) throws UserNotFoundException;

}
