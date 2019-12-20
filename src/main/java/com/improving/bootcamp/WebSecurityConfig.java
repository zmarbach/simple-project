package com.improving.bootcamp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN = "admin";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/static/*", "/api/**").permitAll()
                .antMatchers("/add").hasRole(ADMIN)//only Admin users can access "/add" path
                .anyRequest().authenticated() //ALL other pages require login
                .and()
                .formLogin()
                .defaultSuccessUrl("/home", false)
                .and()
                .logout().logoutSuccessUrl("/home")
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles(ADMIN).and()//roles can be named anything
                .withUser("user").password("{noop}password").roles("USER");
    }
}
