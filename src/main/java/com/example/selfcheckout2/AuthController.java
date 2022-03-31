package com.example.selfcheckout2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    //@Resource(name = "authService")
    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signup")
    public MessageResponse registerUser(@RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }
}
