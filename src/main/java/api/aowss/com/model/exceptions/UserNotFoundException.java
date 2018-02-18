package api.aowss.com.model.exceptions;

public class UserNotFoundException extends RuntimeException {

    private String userId;

    public UserNotFoundException(String userId) {
        super("No user with the following id exists : [ id = " + userId + " ]");
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
