package za.co.discovery.bank.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * This class defines the Security configuration for the application
 * @author Torti Ama-Njoku @ Discovery
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeRequests()
            .anyRequest().permitAll()
            .and()
          .httpBasic().and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
