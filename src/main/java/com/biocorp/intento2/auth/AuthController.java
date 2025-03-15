package com.biocorp.intento2.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp(
            @RequestBody SignUpRequest request
    ){
        JwtAuthResponse response = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(
            @RequestBody SignInRequest request
    ){
        JwtAuthResponse response = authService.signin(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
