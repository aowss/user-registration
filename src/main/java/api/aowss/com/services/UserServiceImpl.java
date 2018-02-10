package api.aowss.com.services;

import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.representations.UserSummary;

import java.util.concurrent.CompletableFuture;

public class UserServiceImpl implements UserService {

    @Override
    public CompletableFuture<Void> registerUser(UserSummary userSummary) throws UserAlreadyExistsException {
        return null;
    }

}
