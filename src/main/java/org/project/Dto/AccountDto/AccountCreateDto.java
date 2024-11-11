package org.project.Dto.AccountDto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.Entity.User;

@Data
public class AccountCreateDto {
    @ManyToOne
    private User owner;
}
