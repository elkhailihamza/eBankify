package org.project.Dto.request.AuthDto;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
}
