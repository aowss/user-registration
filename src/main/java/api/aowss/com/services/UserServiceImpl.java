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
            thenApply(user -> {
                if (user != null) throw new UserAlreadyExistsException(userSummary.getEmail());
                return userStore.save(userSummary);
            }).
            thenApply(User::getId);
    }

    @Override
    public CompletableFuture<User> retrieveUserById(Long userId) throws UserNotFoundException {
        return userStore.findById(userId).
                map(user -> CompletableFuture.completedFuture(user)).
                orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public CompletableFuture<User> retrieveUserByEmail(String email) throws UserNotFoundException {
        return userStore.findByEmail(email).thenApply(user -> {
            if (user == null) throw new UserNotFoundException("email", email);
            return user;
        });
    }

    @Override
    public Long updateUser(User user) {
        return userStore.save(user).getId();
    }

}
