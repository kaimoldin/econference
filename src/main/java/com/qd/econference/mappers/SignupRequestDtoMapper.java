package com.qd.econference.mappers;

import com.qd.econference.domain.SignupRequest;
import com.qd.econference.dto.SignupRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface SignupRequestDtoMapper {
    SignupRequest mapToSignupRequest(SignupRequestDto dto);
}
