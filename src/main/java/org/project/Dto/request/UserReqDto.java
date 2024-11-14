package org.project.Dto.request;

import lombok.Data;
import org.project.Enum.Role;

@Data
public class UserReqDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private Role role = Role.USER;
}
