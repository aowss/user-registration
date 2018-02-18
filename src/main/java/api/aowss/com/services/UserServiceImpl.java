package api.aowss.com.services;

import api.aowss.com.model.User;
import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.model.exceptions.UserNotFoundException;

import api.aowss.com.store.UserStore;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    UserStore userStore;

    @Override
    public CompletableFuture<Long> registerUser(User userSummary) throws UserAlreadyExistsException {
        return userStore.findByEmail(userSummary.getEmail()).
            handle( (user, exception) -> {
                if (user != null) throw new UserAlreadyExistsException(userSummary.getEmail());
                return userStore.save(userSummary).getId();
            });
    }

    @Override
    public CompletableFuture<User> retrieveUser(String userId) throws UserNotFoundException {
        return userStore.findById(userId);
    }

}
