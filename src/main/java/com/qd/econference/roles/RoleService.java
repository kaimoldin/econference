package com.qd.econference.roles;

import com.qd.econference.auth.Role;
import com.qd.econference.auth.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    public static final String PARTICIPANT_ROLE_NAME = "participant";
    private final RoleRepository roleRepository;

    public Role getParticipantRole() {
        return roleRepository.findByName(PARTICIPANT_ROLE_NAME);
    }
}
