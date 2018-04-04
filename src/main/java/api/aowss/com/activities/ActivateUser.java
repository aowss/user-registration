package api.aowss.com.activities;

import api.aowss.com.model.AccountStatus;
import api.aowss.com.model.Token;
import api.aowss.com.model.User;
import api.aowss.com.model.exceptions.*;
import api.aowss.com.services.TokenService;
import api.aowss.com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
public class ActivateUser {

    private static final Logger logger = LoggerFactory.getLogger(ActivateUser.class);

    @Inject
    UserService userService;
    @Inject
    TokenService tokenService;

    public CompletableFuture<Long> updateStatus(Long userId, AccountStatus toStatus, Long tokenId) throws UserNotFoundException, TokenNotFoundException {

        CompletableFuture<User> userFuture = userService.retrieveUserById(userId);
        CompletableFuture<Token> tokenFuture = tokenService.retrieveTokenById(tokenId);

        CompletableFuture<User> future = userFuture.thenCombine(tokenFuture, (user, token) -> {
            if (token.getTokenExpiryDate().isAfter(LocalDateTime.now())) throw new TokenExpiredException(tokenId, token.getTokenExpiryDate());
            if (user.getStatus() != token.getFromStatus()) throw new InvalidTokenException(tokenId, user.getStatus());
            user.setStatus(token.getToStatus());
            return user;
        });

        return future.thenApply(user -> userService.updateUser(user));

    }

}
