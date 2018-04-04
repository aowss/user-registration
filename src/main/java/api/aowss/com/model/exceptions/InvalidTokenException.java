package api.aowss.com.model.exceptions;

import api.aowss.com.model.AccountStatus;

public class InvalidTokenException extends RuntimeException {

    private Long tokenId;
    private AccountStatus fromStatus;

    public InvalidTokenException(Long tokenId, AccountStatus fromStatus) {
        super("The token with the following id : [ id = " + tokenId + " ] isn't valid for the user's current status " + fromStatus);
        this.tokenId = tokenId;
        this.fromStatus = fromStatus;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public AccountStatus getFromStatus() {
        return fromStatus;
    }

}

