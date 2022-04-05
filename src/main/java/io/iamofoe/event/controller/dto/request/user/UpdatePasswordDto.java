package io.iamofoe.event.controller.dto.request.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdatePasswordDto {
    String email;
    String password;
}
