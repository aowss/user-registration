package api.aowss.com;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RequestValidationTest {

    private static final Logger logger = LoggerFactory.getLogger(RequestValidationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void valid() throws Exception {

        MvcResult result = mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{ \"firstName\": \"Aowss\", \"lastName\": \"Ibrahim\", \"email\": \"aowss-1@yahoo.com\", \"password\": \"My-Passw0rd\", \"matchingPassword\": \"My-Passw0rd\" }")
            ).
            andReturn();

        mockMvc.
            perform(asyncDispatch(result)).
            andExpect(status().isCreated());

    }

    @Test
    public void weakPassword() throws Exception {
        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{ \"firstName\": \"Aowss\", \"lastName\": \"Ibrahim\", \"email\": \"aowss@yahoo.com\", \"password\": \"test\", \"matchingPassword\": \"test\" }")
            ).
            andExpect(status().isBadRequest());
    }

    @Test
    public void missingProperty() throws Exception {
        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{ \"lastName\": \"Ibrahim\", \"email\": \"aowss@yahoo.com\", \"password\": \"test\", \"matchingPassword\": \"test\" }")
            ).
            andExpect(status().isBadRequest());
    }

    @Test
    public void missingPasswordProperty() throws Exception {
        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{ \"firstName\": \"Aowss\", \"lastName\": \"Ibrahim\", \"email\": \"aowss@yahoo.com\", \"matchingPassword\": \"test\" }")
            ).
            andExpect(status().isBadRequest());
    }

    @Test
    public void invalidEmail() throws Exception {
        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{ \"firstName\": \"Aowss\", \"lastName\": \"Ibrahim\", \"email\": \"aowss@yahoo\", \"password\": \"test\", \"matchingPassword\": \"test\" }")
            ).
            andExpect(status().isBadRequest());
    }

    @Test
    public void nonMatchingPasswords() throws Exception {
        mockMvc.
            perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{ \"firstName\": \"Aowss\", \"lastName\": \"Ibrahim\", \"email\": \"aowss@yahoo.com\", \"password\": \"test\", \"matchingPassword\": \"tests\" }")
            ).
            andExpect(status().isBadRequest());
    }

}
