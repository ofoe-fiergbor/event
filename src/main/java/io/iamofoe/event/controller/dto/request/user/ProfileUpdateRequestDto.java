package io.iamofoe.event.controller.dto.request.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProfileUpdateRequestDto {
    String firstName;
    String lastName;
    String pictureUrl;
    String phoneNumber;
}
