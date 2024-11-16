package org.project.ebankify.dto.request.authdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {

    @NotNull(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;

    private String surname;

    @NotNull(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Min(value = 14, message = "Age must be at least 14")
    private int age;
}
