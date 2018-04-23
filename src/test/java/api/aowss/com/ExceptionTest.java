package api.aowss.com;

import api.aowss.com.model.User;
import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.model.exceptions.UserNotFoundException;
import api.aowss.com.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.json.Json;
import javax.json.JsonObject;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static api.aowss.com.Utils.perform;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    @MockBean
    private UserService mockService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private MediaType jsonMediaType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.
            webAppContextSetup(context).
            apply(springSecurity()).
            build();
    }

    @Test
    public void alreadyExistingEmail() throws Exception {

        when(mockService.registerUser(any())).thenThrow(new CompletionException(new UserAlreadyExistsException("aowss@yahoo.com")));

        JsonObject user = Json.createObjectBuilder().
            add("firstName",       "Aowss").
            add("lastName",        "Ibrahim").
            add("email",           "irrelevant@yahoo.com").
            add("password",        "My-Passw0rd").
            add("matchingPassword","My-Passw0rd").
            build();

        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content(user.toString())
            ).
            andExpect(status().isForbidden()).
            andExpect(content().contentType(jsonMediaType)).
            andExpect(jsonPath("code", is("EMAIL_ALREADY_EXISTS"))).
            andExpect(jsonPath("email", is("aowss@yahoo.com")));

    }

    @Test
    public void invalidCredentials() throws Exception {

        CompletableFuture<User> completableFuture = new CompletableFuture<>();
        completableFuture.complete(new User());
        when(mockService.retrieveUserByEmail("aowss@yahoo.com")).thenReturn(completableFuture);

        mockMvc.
            perform(
                get("/user/0").with(httpBasic("aowss@yahoo.com", "wrong")).
                contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isUnauthorized())
            .andExpect(jsonPath("code", is("WRONG_CREDENTIALS")));

    }

    @Test
    @WithMockUser(username = "aowss@yahoo.com", password = "My-Passw0rd")
    public void wrongId() throws Exception {

        when(mockService.retrieveUserById(0L)).thenThrow(new CompletionException(new UserNotFoundException(0L)));

        ResultActions mvcResult = perform(
            get("/user/0").accept(MediaType.APPLICATION_JSON),
            mockMvc
        );

        mvcResult.andExpect(status().isNotFound()).
            andExpect(content().contentType(jsonMediaType)).
            andExpect(jsonPath("code", is("USER_NOT_FOUND"))).
            andExpect(jsonPath("id", is("0")));

    }

}
