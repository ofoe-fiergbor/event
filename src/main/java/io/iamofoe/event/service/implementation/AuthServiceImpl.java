package io.iamofoe.event.service.implementation;

import io.iamofoe.event.controller.dto.request.user.*;
import io.iamofoe.event.controller.dto.response.ProfileResponseDto;
import io.iamofoe.event.controller.dto.response.user.*;
import io.iamofoe.event.converter.UserToProfileResponseDtoConverter;
import io.iamofoe.event.converter.UserToProfileUpdateResponseDtoConverter;
import io.iamofoe.event.domain.model.*;
import io.iamofoe.event.domain.repository.UserRepository;
import io.iamofoe.event.service.AuthService;
import io.iamofoe.event.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserToProfileResponseDtoConverter userToProfileResponseDtoConverter;
    private final UserToProfileUpdateResponseDtoConverter userToProfileUpdateResponseDtoConverter;

    @Override
    public AuthResponseDto registerUser(AuthRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists with this email");
        }
        User user = userRepository.save(new User()
                .setEmail(requestDto.getEmail())
                .setPassword(passwordEncoder.encode(requestDto.getPassword()))
                .setRole(Role.USER));
        manageAuthentication(requestDto);
        return AuthResponseDto.builder()
                .id(user.getId())
                .email(requestDto.getEmail())
                .token(jwtUtil.generateAccessToken(user.getEmail()))
                .build();
    }

    @Override
    public AuthResponseDto authenticateUser(AuthRequestDto requestDto) {
        if (!userRepository.existsByEmail(requestDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User doesn't exist");
        }
        User user = userRepository.getUserByEmail(requestDto.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"));
        manageAuthentication(requestDto);
        return AuthResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .pictureUrl(user.getPictureUrl())
                .token(jwtUtil.generateAccessToken(requestDto.getEmail()))
                .build();
    }
    @Override
    public ProfileUpdateResponseDto updateUserProfile(ProfileUpdateRequestDto requestDto) {
        User user = getCurrentUser()
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"))
                .setFirstName(requestDto.getFirstName())
                .setLastName(requestDto.getLastName())
                .setPhoneNumber(requestDto.getPhoneNumber())
                .setPictureUrl(requestDto.getPictureUrl());
        return userToProfileUpdateResponseDtoConverter.convert(userRepository.save(user));
    }

    @Override
    public ProfileResponseDto getUserProfile() {
        User user = getCurrentUser()
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"));
        return userToProfileResponseDtoConverter.convert(user);
    }

    @Override
    public Boolean checkIfUserExist(ProfileCheckRequestDto requestDto) {
        return userRepository.existsByEmail(requestDto.getEmail());
    }

    @Override
    public Boolean updatePassword(UpdatePasswordDto requestDto) {
        User user = getCurrentUser()
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"))
                .setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.getUserByEmail(email);
    }

    private void manageAuthentication(AuthRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword());
        authenticationManager.authenticate(authenticationToken);
    }
}
