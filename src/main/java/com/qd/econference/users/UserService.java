package com.qd.econference.users;

import com.qd.econference.auth.User;
import com.qd.econference.auth.UserRepository;
import com.qd.econference.roles.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
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
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user by id: " + id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
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
                .orElseThrow(() -> new NoSuchElementException("user by id: " + userId));
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
