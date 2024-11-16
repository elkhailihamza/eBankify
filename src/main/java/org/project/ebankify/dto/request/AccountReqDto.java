package org.project.ebankify.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.project.ebankify.entity.User;

@Data
public class AccountReqDto {
    @NotNull(message = "Owner is required")
    private User owner;
}
