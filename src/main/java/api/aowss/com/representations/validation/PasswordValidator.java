package api.aowss.com.representations.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private Pattern pattern;
    private Matcher matcher;

    /**
     * The password must conform to the following rules :
     * - at least 8 chars
     * - contain at least one digit
     * - contain at least one lower alpha char and one upper alpha char
     * - contain at least one char within a set of special chars (@#%$^ etc.)
     * - does not contain space, tab, etc.
     */
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=\\-_])(?=\\S+$).{8,}$";

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context){
        return (validateEmail(password));
    }

    private boolean validateEmail(String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}