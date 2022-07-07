package com.qd.econference.users;

import com.qd.econference.auth.User;
import com.qd.econference.roles.RoleDtoMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {RoleDtoMapper.class})
public interface UserDtoMapper {
    UserDto mapToUserDto(User user);
}
