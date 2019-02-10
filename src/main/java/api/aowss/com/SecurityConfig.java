package api.aowss.com;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private CustomAuthenticationProvider authProvider;

    @Inject
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests().
                //failureHandler(authenticationFailureHandler()).
                        antMatchers(HttpMethod.POST, "/user").permitAll().
                anyRequest().fullyAuthenticated().
            and().
                httpBasic().authenticationEntryPoint(authenticationEntryPoint).
            and().
                headers().cacheControl().disable(). //  This is needed to allow controllers to set the cache control headers
//            and().
//                formLogin().
//                failureHandler(authenticationFailureHandler()).
            and().
                csrf().disable();
    }
/*
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationEntryPoint enntryPoint() {
        return new BasicAuthenticationEntryPoint();
    }
*/
}
