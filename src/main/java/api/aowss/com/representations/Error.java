package api.aowss.com.representations;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    public enum ErrorCode {
        EMAIL_ALREADY_EXISTS,
        USER_NOT_FOUND,
        INALID_FORMAT;
    }

    private ErrorCode code;

    private Map<String, String> details;

    public Error() {
    }

    public Error(ErrorCode code, Map<String, String> details) {
        this.code = code;
        this.details = details;
    }

    public ErrorCode getCode() {
        return code;
    }

    @JsonAnyGetter
    public Map<String, String> getDetails() {
        return details;
    }

}
