package org.project.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private int age;
    private double monthlyIncome = 0;
    private int creditScore = 0;
}
