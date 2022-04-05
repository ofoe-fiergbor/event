package io.iamofoe.event.service;

import io.iamofoe.event.controller.dto.request.user.AuthRequestDto;
import io.iamofoe.event.controller.dto.request.user.ProfileCheckRequestDto;
import io.iamofoe.event.controller.dto.request.user.ProfileUpdateRequestDto;
import io.iamofoe.event.controller.dto.request.user.UpdatePasswordDto;
import io.iamofoe.event.controller.dto.response.ProfileResponseDto;
import io.iamofoe.event.controller.dto.response.user.AuthResponseDto;
import io.iamofoe.event.controller.dto.response.user.ProfileUpdateResponseDto;
import io.iamofoe.event.domain.model.User;

import java.util.Optional;

public interface AuthService {
    AuthResponseDto registerUser(AuthRequestDto requestDto);
    AuthResponseDto authenticateUser(AuthRequestDto requestDto);
    Optional<User> getCurrentUser();
    ProfileUpdateResponseDto updateUserProfile(ProfileUpdateRequestDto requestDto);
    ProfileResponseDto getUserProfile();
    Boolean checkIfUserExist(ProfileCheckRequestDto requestDto);
    Boolean updatePassword(UpdatePasswordDto requestDto);
}
