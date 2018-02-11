package api.aowss.com.resources;

import api.aowss.com.activities.CreateUser;
import api.aowss.com.representations.UserSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public class UserResource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Inject
    CreateUser createUserActivity;

    public UserResource() {
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<ResponseEntity<Object>> register(@RequestBody @Valid UserSummary user) {
        return createUserActivity.createUser(user).thenApply(done -> ResponseEntity.created(null).build());
    }

}