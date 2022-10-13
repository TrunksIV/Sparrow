package com.logistics.Authentication;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;
    @PostMapping("/{id}")
    public ResponseEntity<?> registerUser(@PathVariable Long orgId, @RequestBody Authentication authentication){
    return authenticationService.register(orgId, authentication);
    }
}
