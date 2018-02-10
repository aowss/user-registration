package api.aowss.com.model.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private String email;

    public UserAlreadyExistsException(String email) {
        super("A user with the same email already exists : [ email = " + email + " ]");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
