package io.iamofoe.event.controller.dto.request.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProfileCheckRequestDto {
    String email;
}
