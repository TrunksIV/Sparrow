package com.logistics.Users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final static  String USER_NOT_FOUND ="User with email %s not found";
    private final UserDTO userDTO;
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userDTO.findByEmail(email)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email))
                );
    }

}
