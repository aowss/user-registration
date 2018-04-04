package api.aowss.com.model.exceptions;

import java.time.LocalDateTime;

public class TokenExpiredException extends RuntimeException {

    private Long tokenId;
    private LocalDateTime expiryTime;

    public TokenExpiredException(Long tokenId, LocalDateTime expiryTime) {
        super("The token with the following id : [ id = " + tokenId + " ] is expired since " + expiryTime);
        this.tokenId = tokenId;
        this.expiryTime = expiryTime;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

}

