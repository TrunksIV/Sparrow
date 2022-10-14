package com.logistics.Users;

import com.logistics.Helpers.CustomResponse;
import com.logistics.Organisations.Organisation;
import com.logistics.Organisations.OrganisationDTO;
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
    private final OrganisationDTO organisationDTO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userDTO.findByEmail(email)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email))
                );
    }

    public Object signUpUser(Long id,User user){
        CustomResponse response = new CustomResponse();
        try {
            boolean userExist = userDTO.findByEmail(user.getEmail()).isPresent();
            if(userExist){
                response.setResponse(0);
                response.setMessage(String.format(USER_EXIST,user.getEmail()));
            }else {
                Organisation org = organisationDTO.findById(id).get();
                String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

                user.setPassword(encodedPassword);
                user.setOrganisation(org);
                userDTO.save(user);

                response.setResponse(1);
                response.setMessage("User successfully created");
            }
        }catch (Exception ex){
            response.setResponse(0);
            response.setMessage("Something went wrong");
        }
        return response;
    }

}
