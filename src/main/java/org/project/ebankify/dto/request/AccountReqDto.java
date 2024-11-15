package org.project.ebankify.dto.request;

import lombok.Data;
import org.project.ebankify.entity.User;

@Data
public class AccountReqDto {
    private User owner;
}
