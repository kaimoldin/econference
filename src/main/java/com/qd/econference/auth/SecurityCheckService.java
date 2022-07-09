package com.qd.econference.auth;

import com.qd.econference.roles.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SecurityCheckService {
    private final RoleService roleService;

    public boolean canReadAndUpdateUser(BigInteger userId, User authUser) {
        return authUser.getRoles().contains(roleService.getAdminRole()) || authUser.getId().equals(userId);
    }

    public boolean canCancelRegistration(BigInteger userId, User authUser) {
        return authUser.getRoles().contains(roleService.getAdminRole())
                || authUser.getRoles().contains(roleService.getManagerRole())
                || Objects.equals(userId, authUser.getId());
    }
}
