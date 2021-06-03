package ru.eroonda.ticketmachine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.service.UserService;

import java.util.Collection;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();

        User user = userService.findByEmail(email);

        if (user == null) {
            throw new BadCredentialsException(SpringSecurityMessageSource
                    .getAccessor()
                    .getMessage("AbstractUserDetailsAuthenticationProvider.UserUnknown",
                            new Object[]{email},
                            "Incorrect login (email)"));
        }

        String password = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(SpringSecurityMessageSource
                    .getAccessor()
                    .getMessage("AbstractUserDetailsAuthenticationProvider.BadPassword",
                            new Object[]{password},
                            "Incorrect password"));
        }

        if (!user.isEnabled()) {
            throw new BadCredentialsException(SpringSecurityMessageSource
                    .getAccessor()
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
                            new Object[]{email},
                            "User account not activated yet"));
        }
        //TODO: в  message.properties потом добавить:
//        AbstractUserDetailsAuthenticationProvider.UserUnknown = {0} was not found.
//        AbstractUserDetailsAuthenticationProvider.badCredentials = Password is bad
//        AbstractUserDetailsAuthenticationProvider.credentialsExpired = User credentials have expired
//        AbstractUserDetailsAuthenticationProvider.disabled = User is disabled
//        AbstractUserDetailsAuthenticationProvider.expired = User account has expired
//        AbstractUserDetailsAuthenticationProvider.locked = User account is locked


        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
