package com.qd.econference.mappers;

import com.qd.econference.domain.UpdateUserRequest;
import com.qd.econference.domain.User;
import com.qd.econference.dto.AddUserRequestDto;
import com.qd.econference.dto.UpdateUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RoleDtoMapper.class})
public interface UserRequestDtoMapper {
    @Mapping(target = "roles", ignore = true)
    User mapToAddUserRequest(AddUserRequestDto dto);

    UpdateUserRequest mapUpdateUserRequest(UpdateUserRequestDto dto);
}
