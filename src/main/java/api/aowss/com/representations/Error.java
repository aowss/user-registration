package api.aowss.com.representations;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Map;

public class Error {

    private String code;

    private Map<String, String> details;

    public Error() {
    }

    public Error(String code, Map<String, String> details) {
        this.code = code;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    @JsonAnyGetter
    public Map<String, String> getDetails() {
        return details;
    }

}
