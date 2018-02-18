package api.aowss.com.activities;

import api.aowss.com.model.User;
import api.aowss.com.representations.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FromModel {

    public static Function<User, UserRepresentation> toUserRepresentation = user -> new UserRepresentation(user.getFirstName(), user.getLastName(), user.getEmail());

}
