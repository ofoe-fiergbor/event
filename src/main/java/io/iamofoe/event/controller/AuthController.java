package io.iamofoe.event.controller;

import io.iamofoe.event.controller.dto.request.user.AuthRequestDto;
import io.iamofoe.event.controller.dto.request.user.ProfileCheckRequestDto;
import io.iamofoe.event.controller.dto.request.user.ProfileUpdateRequestDto;
import io.iamofoe.event.controller.dto.request.user.UpdatePasswordDto;
import io.iamofoe.event.controller.dto.response.ProfileResponseDto;
import io.iamofoe.event.controller.dto.response.user.AuthResponseDto;
import io.iamofoe.event.controller.dto.response.user.ProfileUpdateResponseDto;
import io.iamofoe.event.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@Valid @RequestBody AuthRequestDto requestDto) {
        return new ResponseEntity<>(authService.registerUser(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticateExistingUser(@Valid @RequestBody AuthRequestDto requestDto) {
        return new ResponseEntity<>(authService.authenticateUser(requestDto), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDto> getUserProfile(){
        return new ResponseEntity<>(authService.getUserProfile(), HttpStatus.OK);
    }

    @PostMapping("/profile/update")
    public ResponseEntity<ProfileUpdateResponseDto> updateUserProfile(@RequestBody ProfileUpdateRequestDto requestDto) {
        return new ResponseEntity<>(authService.updateUserProfile(requestDto), HttpStatus.OK);
    }

    @PostMapping("/profile/check")
    public ResponseEntity<Boolean> checkIfUserExists(@RequestBody ProfileCheckRequestDto requestDto){
        return new ResponseEntity<>(authService.checkIfUserExist(requestDto), HttpStatus.OK);
    }
    @PostMapping("profile/password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordDto requestDto) {
        return new ResponseEntity<>(authService.updatePassword(requestDto), HttpStatus.OK);
    }
 }
