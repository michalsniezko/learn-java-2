package com.michalsniezko.schoolapp.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
        @NotEmpty(message = "First name cannot be empty!")
        String firstName,
        @NotEmpty
        String lastName,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        Integer schoolId
) {}