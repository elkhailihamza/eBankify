package org.project.Dto.request;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.Entity.User;

@Data
public class AccountReqDto {
    @ManyToOne
    private User owner;
}
