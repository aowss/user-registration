package api.aowss.com;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.core.IsAnything.anything;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

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

    public static ResultActions perform(MockHttpServletRequestBuilder builder, MockMvc mockMvc) throws Exception {
        ResultActions resultActions = mockMvc.perform(builder);
        if (resultActions.andReturn().getRequest().isAsyncStarted()) {
            return mockMvc.perform(asyncDispatch(resultActions
                    .andExpect(request().asyncResult(anything()))
                    .andReturn()));
        } else {
            return resultActions;
        }
    }

}
