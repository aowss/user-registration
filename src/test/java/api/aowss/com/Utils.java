package api.aowss.com;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class Utils {

    public static String getResponseAsString(String url, MockMvc mockMvc) throws Exception {
        MvcResult result = mockMvc
                .perform(get(url))
                .andReturn();

        return mockMvc
                .perform(asyncDispatch(result))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
