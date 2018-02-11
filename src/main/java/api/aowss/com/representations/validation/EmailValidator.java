package api.aowss.com.representations.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return (validateEmail(email));
    }

    private boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}