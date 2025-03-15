package com.biocorp.intento1.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "el primer nombre no debe estar vacio")
        String firstname,
        @NotEmpty(message = "apellido pa")
        String lastname,
        String email,
        Integer schoolId
){
}
