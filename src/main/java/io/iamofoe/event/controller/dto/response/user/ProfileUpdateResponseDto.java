package io.iamofoe.event.controller.dto.response.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProfileUpdateResponseDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String pictureUrl;
}
