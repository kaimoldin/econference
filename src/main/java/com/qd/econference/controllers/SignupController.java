package com.qd.econference.controllers;

import com.qd.econference.dto.SignupRequestDto;
import com.qd.econference.dto.UserDto;
import com.qd.econference.mappers.SignupRequestDtoMapper;
import com.qd.econference.mappers.UserDtoMapper;
import com.qd.econference.services.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.qd.econference.controllers.RestConstants.API_V1_PATH_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PATH_PREFIX + "/signup")
public class SignupController {

    private final SignupService signupService;
    private final UserDtoMapper userDtoMapper;
    private final SignupRequestDtoMapper signupRequestDtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto signup(@RequestBody @Valid SignupRequestDto request) {
        return userDtoMapper.mapToUserDto(signupService.signup(signupRequestDtoMapper.mapToSignupRequest(request)));
    }
}
