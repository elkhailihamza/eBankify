package org.project.ebankify.dto.request.authdto;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
}
