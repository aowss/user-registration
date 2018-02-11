package api.aowss.com;

import api.aowss.com.services.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("test")
@Configuration
public class ServiceTestConfig {

    @Bean
    @Primary
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

}