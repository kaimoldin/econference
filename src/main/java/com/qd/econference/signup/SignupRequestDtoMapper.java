package com.qd.econference.signup;

import org.mapstruct.Mapper;

@Mapper
public interface SignupRequestDtoMapper {
    SignupRequest mapToSignupRequest(SignupRequestDto dto);
}
