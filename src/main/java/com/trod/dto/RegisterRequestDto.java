package com.trod.dto;

import com.trod.constant.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Profile;

public record RegisterRequestDto (
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String password,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @Schema(defaultValue = "PLAYER")
        RoleEnum role
) {
        public RegisterRequestDto(
                String username,
                String password,
                String email
        ) {
                this(username, password, email, RoleEnum.PLAYER);
        }
}
