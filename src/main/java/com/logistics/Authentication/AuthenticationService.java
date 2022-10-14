package com.logistics.Authentication;

import com.logistics.Helpers.CustomResponse;
import com.logistics.Organisations.Organisation;
import com.logistics.Organisations.OrganisationDTO;
import com.logistics.Organisations.OrganisationService;
import com.logistics.Users.Roles;
import com.logistics.Users.User;
import com.logistics.Users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final OrganisationDTO organisationDTO;
    private final EmailValidator emailValidator;

    public ResponseEntity register(Long id,Authentication authentication){
        boolean isEmailValid = emailValidator.test(authentication.getEmail());
        CustomResponse response = new CustomResponse();
        if(!isEmailValid) {
            response.setResponse(0);
            response.setMessage("Please provide a valid email");
            return ResponseEntity.badRequest().body(response);
        }

//        Get Organisation

        Object user = userService.signUpUser(
                id,
                new User(
                        authentication.getFirstName(),
                        authentication.getLastName(),
                        authentication.getAddress(),
                        authentication.getEmail(),
                        authentication.getPhone(),
                        authentication.getPassword(),
                        Roles.ADMIN,
                        true
                )
        );
        return ResponseEntity.ok(user);
    }
}
