package api.aowss.com.services;

import api.aowss.com.model.User;
import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.representations.UserSummary;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<Void> registerUser(User userSummary) throws UserAlreadyExistsException {
        if (users.containsKey(userSummary.getEmail())) throw new UserAlreadyExistsException(userSummary.getEmail());
        users.put(userSummary.getEmail(), userSummary);
        return CompletableFuture.completedFuture(null);
    }

}
