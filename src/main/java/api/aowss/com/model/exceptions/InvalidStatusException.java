package api.aowss.com.model.exceptions;

import api.aowss.com.model.AccountStatus;

public class InvalidStatusException extends RuntimeException {

    private AccountStatus status;

    public InvalidStatusException(AccountStatus status) {
        super("The user's account status is invalid : [ status = " + status + " ]");
        this.status = status;
    }

    public AccountStatus getStatus() {
        return status;
    }

}
