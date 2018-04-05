package api.aowss.com.services;

import api.aowss.com.model.Token;
import api.aowss.com.model.exceptions.TokenNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TokenServiceImpl implements TokenService{

    @Override
    public CompletableFuture<Long> createToken(Token token) {
        return null;
    }

    @Override
    public CompletableFuture<Token> retrieveTokenById(Long tokenId) throws TokenNotFoundException {
        return null;
    }

}
