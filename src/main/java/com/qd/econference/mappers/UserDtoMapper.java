package com.qd.econference.mappers;

import com.qd.econference.domain.User;
import com.qd.econference.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(uses = {RoleDtoMapper.class})
public interface UserDtoMapper {
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto dto);
}
