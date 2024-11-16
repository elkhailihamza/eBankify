package org.project.ebankify.dto.request.authdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @NotNull(message = "Email must not be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password must not be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
