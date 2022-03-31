package com.example.selfcheckout2;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    MessageResponse registerUser(SignupRequest signUpRequest);
}
