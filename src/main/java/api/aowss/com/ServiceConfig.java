package api.aowss.com;

import api.aowss.com.services.UserService;
import api.aowss.com.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

}