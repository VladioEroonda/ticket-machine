package ru.eroonda.ticketmachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.eroonda.ticketmachine.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.eroonda.ticketmachine.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                    .antMatchers("/ticket_machine/**").authenticated() //.hasRole("USER")
//                    .antMatchers("/auth/*").anonymous()
//                    .antMatchers("/").permitAll()
//                    .anyRequest().authenticated() эти 5 работают + csrf nije

                .authorizeRequests()
                .antMatchers("/ticket_machine/**").authenticated()
                .antMatchers("/auth/*").anonymous()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                .formLogin()
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/login/process")
                    .defaultSuccessUrl("/ticket_machine")
                    .usernameParameter("email")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/ticket_machine")
                .and()
                    .logout();
//        .permitAll();
        //        .logout()
        //            .logoutUrl("/logout")
        //        .logoutSuccessUrl("/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
