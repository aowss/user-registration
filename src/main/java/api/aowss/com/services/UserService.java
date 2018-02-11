package api.aowss.com.services;

import api.aowss.com.model.User;
import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.representations.UserSummary;

import java.util.concurrent.CompletableFuture;

public interface UserService {

    CompletableFuture<Void> registerUser(User userSummary) throws UserAlreadyExistsException;

}
