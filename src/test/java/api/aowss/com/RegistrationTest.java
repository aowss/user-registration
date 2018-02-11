package api.aowss.com;

import api.aowss.com.model.exceptions.UserAlreadyExistsException;
import api.aowss.com.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(classes={ServiceTestConfig.class})
public class RegistrationTest {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    @Autowired
    private UserService mockService;

    @Autowired
    private MockMvc mockMvc;

    private MediaType jsonMediaType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void alreadyExistingEmail() throws Exception {

        when(mockService.registerUser(any())).thenThrow(new UserAlreadyExistsException("aowss@yahoo.com"));

        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{ \"firstName\": \"Aowss\", \"lastName\": \"Ibrahim\", \"email\": \"aowss@yahoo.com\", \"password\": \"My-Passw0rd\", \"matchingPassword\": \"My-Passw0rd\" }")
            ).andExpect(status().isForbidden())
            .andExpect(content().contentType(jsonMediaType))
            .andExpect(jsonPath("code", is("EMAIL_ALREADY_EXISTS")))
            .andExpect(jsonPath("email", is("aowss@yahoo.com")));
    }

}
