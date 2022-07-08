package com.qd.econference.roles;

import com.qd.econference.auth.Role;
import com.qd.econference.auth.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleService {
    public static final String PARTICIPANT_ROLE_NAME = "PARTICIPANT";
    public static final String ADMIN_ROLE_NAME = "ADMIN";
    private final RoleRepository roleRepository;

    public Role getParticipantRole() {
        return getByName(PARTICIPANT_ROLE_NAME);
    }

    public Role getAdminRole() {
        return getByName(ADMIN_ROLE_NAME);
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Role not found: " + name));
    }
}
