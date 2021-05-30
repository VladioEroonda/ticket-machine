package ru.eroonda.ticketmachine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new BadCredentialsException("user Not Found");//TODO:свой ексепшн добавить
        }
        String password = authentication.getCredentials().toString();
        if(!password.equals(user.getPassword())){
            throw new BadCredentialsException("Bad password");//TODO:свой ексепшн добавить
        }
        //TODO:Добавить проверку на енейблд аккаунта
        List<GrantedAuthority> authorityList = new ArrayList<>();
//        authorityList.add()

        return new UsernamePasswordAuthenticationToken(user,null, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
