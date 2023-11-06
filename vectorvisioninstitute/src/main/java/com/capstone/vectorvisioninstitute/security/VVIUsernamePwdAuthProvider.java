package com.capstone.vectorvisioninstitute.security;


import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.model.Roles;
import com.capstone.vectorvisioninstitute.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VVIUsernamePwdAuthProvider implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        //check if email entered by end user is available inside DB.
        Person person = personRepository.readByEmail(email);

        //make sure all three conditions are working
        if(null != person && person.getPersonId() > 0 && passwordEncoder.matches(pwd, person.getPwd())){
            //spring will generate new auth token while also erasing the password credentials
            return new UsernamePasswordAuthenticationToken(
                    //.getName() displays users Name //credentials = null to not add the password details
                    email, null, fetchUserAuthorities(person.getRoles()));
        }else{
            throw new BadCredentialsException("Invalid Credentials!");
        }
    }

    /**
     * Populate Roles
     * @param roles
     * @return grantedAuthorities
     */
    private List<GrantedAuthority> fetchUserAuthorities(Roles roles){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
