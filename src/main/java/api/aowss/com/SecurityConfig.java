package api.aowss.com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                antMatchers("/user/registration").permitAll().
                anyRequest().fullyAuthenticated().
                and().
                httpBasic().
                and().
                headers().cacheControl().disable(). //  This is needed to allow controllers to set the cache control headers
                and().
                csrf().disable();
    }

    @Bean("Authentication")
    public UserDetailsService configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //  If a role is not assigned, you get the following exception : java.lang.IllegalArgumentException: Cannot pass a null GrantedAuthority collection as mentioned in https://stackoverflow.com/questions/38687209/spring-boot-security-java-lang-illegalargumentexception-cannot-pass-a-null-gr
                .withUser("Aowss-ctn").password("password").roles("USER")
                .and()
                .withUser("aowss@rogers.com-login").password("password").roles("USER")
                .and()
                .withUser("Celine-ctn").password("password").roles("USER")
                .and()
                .withUser("Moussa-ctn").password("password").roles("USER")
                .and()
                .withUser("Iman-ctn").password("password").roles("USER")
                .and()
                .withUser("Salman-ctn").password("password").roles("USER");
        return auth.getDefaultUserDetailsService();
    }

}
