package org.project.dto.request;

import lombok.Data;
import org.project.type.Role;

@Data
public class UserReqDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private Role role = Role.USER;
}
