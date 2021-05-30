package ru.eroonda.ticketmachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                .authorizeRequests()
                    .antMatchers("/registration", "/login", "/restore", "/").anonymous()
//                    .antMatchers("/ticket_machine").authenticated()  не работает
//                .antMatchers("/ticket_machine/**").authenticated() работает с натягом
//                .anyRequest().authenticated() не работает


//                .antMatchers("/ticket_machine/**", "/*").authenticated() //.hasRole("USER")
//                .antMatchers("/*").anonymous()
//                .antMatchers("/auth/*").permitAll()
//                .anyRequest().authenticated()

                .antMatchers("/ticket_machine/**").authenticated() //.hasRole("USER")
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

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/", "/auth/registration").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/auth/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll();
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("u")
//                        .password("1")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
