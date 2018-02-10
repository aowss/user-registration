package api.aowss.com.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    private String code;

    @JsonProperty("codeNasme")
    private String name;

    public Error() {
    }

    public Error(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
