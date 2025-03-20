package com.biocorp.intento2.auth;

import com.biocorp.intento2.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role; // just for testing
}
