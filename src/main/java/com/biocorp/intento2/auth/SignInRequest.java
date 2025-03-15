package com.biocorp.intento2.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
