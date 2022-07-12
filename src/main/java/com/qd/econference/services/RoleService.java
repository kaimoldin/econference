package com.qd.econference.services;

import com.qd.econference.domain.Role;
import com.qd.econference.exceptions.RoleNotFoundException;
import com.qd.econference.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    public static final String PARTICIPANT_ROLE_NAME = "PARTICIPANT";
    public static final String ADMIN_ROLE_NAME = "ADMIN";
    public static final String MANAGER_ROLE_NAME = "MANAGER";
    private final RoleRepository roleRepository;

    public Role getParticipantRole() {
        return getByName(PARTICIPANT_ROLE_NAME);
    }

    public Role getAdminRole() {
        return getByName(ADMIN_ROLE_NAME);
    }

    public Role getManagerRole() {
        return getByName(MANAGER_ROLE_NAME);
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }
}
