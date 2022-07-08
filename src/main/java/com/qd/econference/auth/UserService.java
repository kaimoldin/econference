package com.qd.econference.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

    public User getById(BigInteger id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("userId: " + id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User add(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User already exists: " + user);
        }
        return userRepository.save(user);
    }

    public User update(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User not found: " + user);
        }
        return userRepository.save(user);
    }
}
