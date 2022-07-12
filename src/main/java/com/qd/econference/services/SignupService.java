package com.qd.econference.services;

import com.qd.econference.domain.SignupRequest;
import com.qd.econference.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {
    private final UserService userService;
    private final RoleService roleService;

    public User signup(SignupRequest request) {
        return userService.add(User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .roles(Set.of(roleService.getParticipantRole()))
                .enabled(true)
                .build());
    }
}
