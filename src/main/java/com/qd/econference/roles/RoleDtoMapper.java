package com.qd.econference.roles;

import com.qd.econference.auth.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleDtoMapper {
    RoleDto mapToRoleDto(Role role);
}
