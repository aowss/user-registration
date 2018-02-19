package api.aowss.com.model.exceptions;

public class UserNotFoundException extends RuntimeException {

    private Long userId;

    public UserNotFoundException(Long userId) {
        super("No user with the following id exists : [ id = " + userId + " ]");
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
