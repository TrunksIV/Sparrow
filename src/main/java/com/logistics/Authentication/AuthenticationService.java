package com.logistics.Authentication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    public String register(Authentication authentication){
        return "works";
    }
}
