package org.project.ebankify.dto.request;

import lombok.Data;
import org.project.ebankify.type.Role;

@Data
public class UserReqDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private Role role = Role.USER;
}
