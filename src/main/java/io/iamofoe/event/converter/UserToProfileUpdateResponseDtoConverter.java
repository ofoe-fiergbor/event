package io.iamofoe.event.converter;

import io.iamofoe.event.controller.dto.response.user.ProfileUpdateResponseDto;
import io.iamofoe.event.domain.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToProfileUpdateResponseDtoConverter implements Converter<User, ProfileUpdateResponseDto> {
    @Override
    public ProfileUpdateResponseDto convert(User user) {
        return ProfileUpdateResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .pictureUrl(user.getPictureUrl())
                .build();
    }
}
