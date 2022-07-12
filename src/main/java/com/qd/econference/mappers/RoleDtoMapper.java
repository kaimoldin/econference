package com.qd.econference.mappers;

import com.qd.econference.domain.Role;
import com.qd.econference.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoleDtoMapper {
    RoleDto mapToRoleDto(Role role);
}
