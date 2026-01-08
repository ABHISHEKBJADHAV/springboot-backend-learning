package com.quizzApp.auth_service.Model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(
        @NotBlank(message = "Username cannot be empty")
        @Size(min = 4, max = 20, message = "Username must be 4-20 characters")
        String username,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password) {
}
