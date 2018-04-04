package api.aowss.com.model.exceptions;

public class TokenNotFoundException extends RuntimeException {

    private Long tokenId;

    public TokenNotFoundException(Long tokenId) {
        super("No token with the following id exists : [ id = " + tokenId + " ]");
        this.tokenId = tokenId;
    }

    public Long getTokenId() {
        return tokenId;
    }
}

