package org.project.dto.request;

import lombok.Data;
import org.project.entity.User;

@Data
public class AccountReqDto {
    private User owner;
}
