package com.qd.econference.auth;

import com.qd.econference.roles.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class SecurityCheckService {
    private final RoleService roleService;

    public boolean canReadAndUpdateUser(BigInteger userId, User authUser) {
        return authUser.getRoles().contains(roleService.getAdminRole()) || authUser.getId().equals(userId);
    }
}
