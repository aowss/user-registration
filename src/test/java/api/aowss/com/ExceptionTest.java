package api.aowss.com;

import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.model.exceptions.UserNotFoundException;
import api.aowss.com.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.json.Json;
import javax.json.JsonObject;
import java.nio.charset.Charset;
import java.util.concurrent.CompletionException;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    @Autowired
    private UserService mockService;

    @Autowired
    private MockMvc mockMvc;

    private MediaType jsonMediaType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void alreadyExistingEmail() throws Exception {

        when(mockService.registerUser(any())).thenThrow(new CompletionException(new UserAlreadyExistsException("aowss@yahoo.com")));

        JsonObject user = Json.createObjectBuilder()
            .add("firstName",       "Aowss")
            .add("lastName",        "Ibrahim")
            .add("email",           "aowss@yahoo.com")
            .add("password",        "My-Passw0rd")
            .add("matchingPassword","My-Passw0rd")
            .build();

        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content(user.toString())
            ).andExpect(status().isForbidden())
            .andExpect(content().contentType(jsonMediaType))
            .andExpect(jsonPath("code", is("EMAIL_ALREADY_EXISTS")))
            .andExpect(jsonPath("email", is("aowss@yahoo.com")));

    }

    @Test
    public void wrongId() throws Exception {

        when(mockService.retrieveUser(0L)).thenThrow(new CompletionException(new UserNotFoundException(0L)));

        mockMvc.
            perform(
                get("/user/0").
                accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotFound())
            .andExpect(content().contentType(jsonMediaType))
            .andExpect(jsonPath("code", is("USER_NOT_FOUND")))
            .andExpect(jsonPath("id", is("0")));

    }

}
