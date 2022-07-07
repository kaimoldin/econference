package com.qd.econference.signup;

import com.qd.econference.users.UserDto;
import com.qd.econference.users.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/signup")
public class SignupController {

    private final SignupService signupService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping
    UserDto signup(@RequestBody @Valid SignupRequest request) {
        return userDtoMapper.mapToUserDto(signupService.signup(request.getEmail(), request.getName()));
    }
}
