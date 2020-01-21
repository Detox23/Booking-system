package API.Configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //The UserDetailsService interface is used to retrieve user-related data.
    // It has one method named loadUserByUsername() which can be overridden to customize
    // the process of finding the user.
    @Resource(name = "systemUserService")
    private UserDetailsService userDetailsService;

    @Autowired
    //Commences an authentication scheme.
    private UserAuthenticationEntryPoint unauthorizedHandler;

    //In Spring Security, the authentication manager assumes the job of establishing a user's identity.
    //An authentication manager is defined by the org.springframework.security.authentication.AuthenticationManager interface.
    //The authenticate method will attempt to authenticate the user using the org.springframework.security.core.Authentication
    //The authenticate method returns a complete Authentication object, including information about the user's granted authorities.
    //If authentication fails, an authentication exception will be thrown.
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // userDetailsService: Adds authentication based upon the custom UserDetailsService that is passed in.
    // allows specifying the PasswordEncoder to use.
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    //creates a bean of userAuthenticationFilter
    public UserAuthenticationFilter authenticationTokenFilterBean() {
        return new UserAuthenticationFilter();
    }

    //configures http security, sets authorization on endpoints, session management and exception handling
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/api/systemUsers/login", "/api/systemUsers/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    //creates a bean of passwordEncoder
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}