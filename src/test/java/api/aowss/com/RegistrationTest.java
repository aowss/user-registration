package api.aowss.com;

import api.aowss.com.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import javax.json.Json;
import javax.json.JsonObject;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationTest {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    @Autowired
    private UserService mockService;

    @Autowired
    private MockMvc mockMvc;

    private MediaType jsonMediaType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void retrieveUserById() throws Exception {

        JsonObject user = Json.createObjectBuilder()
            .add("firstName",       "Aowss")
            .add("lastName",        "Ibrahim")
            .add("email",           "aowss@yahoo.com")
            .add("password",        "My-Passw0rd")
            .add("matchingPassword","My-Passw0rd")
            .build();

        MvcResult createResult = mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content(user.toString())
            ).andReturn();

        ResultActions result = mockMvc.
            perform(asyncDispatch(createResult));

        result.
            andExpect(status().isCreated());

        String locationHeader = result.andReturn().getResponse().getHeader("Location");

        MvcResult retrieveResult = mockMvc.
            perform(
                get(locationHeader).
                accept(MediaType.APPLICATION_JSON)
            ).andReturn();

        mockMvc.
            perform(asyncDispatch(retrieveResult)).
            andExpect(status().isOk())
            .andExpect(content().contentType(jsonMediaType))
            .andExpect(jsonPath("email", is("aowss@yahoo.com")));

    }

}
