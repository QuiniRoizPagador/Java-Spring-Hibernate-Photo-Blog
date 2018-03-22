package es.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import es.blog.service.UserDetailsServices;
import es.blog.service.UserService;
import org.springframework.http.HttpMethod;

/**
 *
 * @author Quini
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    /**
     *
     * @param auth AuthenticationManagerBuilder from authentication
     * configuration
     * @throws Exception if gives error
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServices(userService));
    }

    /**
     *
     * @param http Security configuration from http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**", "/geoLocation").permitAll()
                .antMatchers(HttpMethod.POST, "/sendMail").permitAll()
                .antMatchers(HttpMethod.GET, "/admin").access("hasRole('ADMIN')")
                // login
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/admin")
                // logout
                .and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID", "SESSION")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                // session
                .and().sessionManagement()
                .sessionAuthenticationErrorUrl("/logout").maximumSessions(1);
        http.headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
    }

}
