package api.aowss.com.activities;

import api.aowss.com.model.User;
import api.aowss.com.representations.UserSummary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.function.Function;

@Component
public class FromRepresentation {

    @Inject
    private static PasswordEncoder passwordEncoder;

    @Inject
    public FromRepresentation(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static Function<UserSummary, User> toUser = userRepresentation -> new User(userRepresentation.getFirstName(), userRepresentation.getLastName(), userRepresentation.getEmail(), passwordEncoder.encode(userRepresentation.getPassword()));

}
