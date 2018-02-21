package api.aowss.com.model.exceptions;

public class UserNotFoundException extends RuntimeException {

    private String idType;
    private String idValue;
    private Long userId;

    public UserNotFoundException(Long userId) {
        this("id", userId + "");
    }

    public UserNotFoundException(String idType, String idValue) {
        super("No user with the following " + idType + " exists : [ " + idType + " = " + idValue + " ]");
        this.idType = idType;
        this.idValue = idValue;
    }

    public Long getUserId() {
        return userId;
    }

    public String getIdType() {
        return idType;
    }

    public String getIdValue() {
        return idValue;
    }

}
