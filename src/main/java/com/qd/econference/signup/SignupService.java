package com.qd.econference.signup;

import com.qd.econference.auth.User;
import com.qd.econference.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;

    public User signup(String email, String name) {
        return userRepository.save(User.builder().email(email).name(name).enabled(true).build());
    }
}
