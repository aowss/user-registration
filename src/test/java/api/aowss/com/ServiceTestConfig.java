package api.aowss.com;

import api.aowss.com.services.UserService;
import api.aowss.com.services.UserServiceImpl;
import api.aowss.com.store.UserStore;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;

@Profile("test")
@Configuration
public class ServiceTestConfig {

    @Bean
    @Primary
    public UserService userService() {
        //return Mockito.mock(UserService.class);
        return Mockito.spy(new UserServiceImpl());
    }

}