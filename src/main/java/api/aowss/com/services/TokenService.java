package api.aowss.com.services;

import api.aowss.com.model.Token;
import api.aowss.com.model.exceptions.TokenNotFoundException;

import java.util.concurrent.CompletableFuture;

public interface TokenService {

    CompletableFuture<Long> createToken(Token token);
    CompletableFuture<Token> retrieveTokenById(Long tokenId) throws TokenNotFoundException;

}
