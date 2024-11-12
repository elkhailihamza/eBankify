package org.project.Dto.request;

import lombok.Data;
import org.project.Entity.User;

@Data
public class AccountReqDto {
    private User owner;
}
