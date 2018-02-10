package api.aowss.com.resources;

import api.aowss.com.activities.CreateUser;
import api.aowss.com.representations.UserSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user/registration")
public class RegistrationResource {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationResource.class);

    @Inject
    CreateUser createUserActivity;

    public RegistrationResource() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<Object>> register(@RequestBody @Valid UserSummary user) {
        return null;
    }

}