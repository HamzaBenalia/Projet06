package com.openclassroom.paymybuddy.configuration;

import com.openclassroom.paymybuddy.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Defining the passwordEncoder here, to avoid plain-text manipulations
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register**",
                        "/login**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/templates/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/", true)
                .usernameParameter("email") //
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "REMEMBERSESSION")
                .and().csrf().disable();
    }

}