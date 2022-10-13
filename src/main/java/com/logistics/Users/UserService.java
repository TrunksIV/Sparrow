package com.logistics.Users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final static  String USER_NOT_FOUND ="User with email %s not found";
    private final static  String USER_EXIST ="Email %s is already taken";

    private final UserDTO userDTO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userDTO.findByEmail(email)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email))
                );
    }

    public String signUpUser(User user){
        boolean userExist = userDTO.findByEmail(user.getEmail()).isPresent();
        if(userExist){
            throw new IllegalStateException(String.format(USER_EXIST,user.getEmail()));
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDTO.save(user);
        return "Created Successfully";
    }

}
