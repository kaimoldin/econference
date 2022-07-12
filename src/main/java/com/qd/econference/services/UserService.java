package com.qd.econference.services;

import com.qd.econference.domain.UpdateUserRequest;
import com.qd.econference.domain.User;
import com.qd.econference.exceptions.UserNotFoundException;
import com.qd.econference.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getById(BigInteger id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User add(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateKeyException("user: " + user);
        }
        return save(user);
    }

    public User addWithRoles(User user, Set<String> roleNames) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(roleNames);
        user.setRoles(roleNames.stream().map(roleService::getByName).collect(toSet()));
        return add(user);
    }

    public User update(BigInteger userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        if (Strings.isNotBlank(request.getName())) {
            user.setName(request.getName());
        }
        if (Strings.isNotBlank(request.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return save(user);
    }

    private User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        savedUser.setPassword(null);
        return savedUser;
    }
}
