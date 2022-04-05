package io.iamofoe.event.controller.dto.response;

import io.iamofoe.event.controller.dto.response.event.EventResponseDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProfileResponseDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String pictureUrl;
    String phoneNumber;
    List<EventResponseDto> events;
}
