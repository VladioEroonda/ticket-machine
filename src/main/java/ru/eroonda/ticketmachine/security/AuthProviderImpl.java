package ru.eroonda.ticketmachine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.service.UserService;

import java.util.ArrayList;
import java.util.List;

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
        if (user == null){
            throw new BadCredentialsException("user Not Found");//TODO:свой ексепшн добавить
        }
        String password = authentication.getCredentials().toString();
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Bad password");//TODO:свой ексепшн добавить
        }
        if(!user.isEnabled()){
            throw new AccountExpiredException("Account was disabled by Admin");//TODO:свой ексепшн добавить
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();

        return new UsernamePasswordAuthenticationToken(user,null, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
