package com.qd.econference.users;

import com.qd.econference.auth.User;
import com.qd.econference.roles.RoleDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RoleDtoMapper.class})
public interface UserRequestDtoMapper {
    @Mapping(target = "roles", ignore = true)
    User mapToAddUserRequest(AddUserRequestDto dto);

    UpdateUserRequest mapUpdateUserRequest(UpdateUserRequestDto dto);
}
