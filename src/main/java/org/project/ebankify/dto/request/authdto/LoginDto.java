package org.project.ebankify.dto.request.authdto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
