package api.aowss.com;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by garga9 on 10/03/2018.
 */

public class ErrorHandler {

    private static Map<Integer, String> errorMap = new HashMap<>();

    static {
        errorMap.put(HttpServletResponse.SC_UNAUTHORIZED, authenticationFailureString());
    }

    public static String customError(int errorCode) {
        return errorMap.get(errorCode);
    }

    private static String authenticationFailureString() {
        JsonObject errorObj = Json.createObjectBuilder()
                .add("code",       "WRONG_CREDENTIALS")
                .build();
        return errorObj.toString();
    }
}
