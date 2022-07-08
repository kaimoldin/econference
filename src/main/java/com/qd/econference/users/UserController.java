package com.qd.econference.users;

import com.qd.econference.roles.RoleDto;
import com.qd.econference.roles.RoleDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Set;
import javax.validation.Valid;

import static java.util.stream.Collectors.toSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;
    private final UserRequestDtoMapper userRequestDtoMapper;
    private final RoleDtoMapper roleDtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    UserDto add(@RequestBody @Valid AddUserRequestDto request) {
        return userDtoMapper.mapToUserDto(
                userService.addWithRoles(userRequestDtoMapper.mapToAddUserRequest(request), request.getRoles()));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("@securityCheckService.canReadAndUpdateUser(#userId, principal)")
    UserDto update(@PathVariable BigInteger userId, @RequestBody @Valid UpdateUserRequestDto request) {
        return userDtoMapper.mapToUserDto(
                userService.update(userId, userRequestDtoMapper.mapUpdateUserRequest(request)));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@securityCheckService.canReadAndUpdateUser(#userId, principal)")
    UserDto get(@PathVariable BigInteger userId) {
        return userDtoMapper.mapToUserDto(userService.getById(userId));
    }

    @GetMapping("/{userId}/roles")
    @PreAuthorize("@securityCheckService.canReadAndUpdateUser(#userId, principal)")
    Set<RoleDto> getRoles(@PathVariable BigInteger userId) {
        return userService.getById(userId).getRoles().stream().map(roleDtoMapper::mapToRoleDto).collect(toSet());
    }
}
