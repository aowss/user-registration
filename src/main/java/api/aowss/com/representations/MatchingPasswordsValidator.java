package api.aowss.com.representations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordsValidator implements ConstraintValidator<MatchingPasswords, Object> {

    @Override
    public void initialize(MatchingPasswords constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserSummary userSummary = (UserSummary)obj;
        return userSummary.getPassword().equals(userSummary.getMatchingPassword());
    }

}
