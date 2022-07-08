package com.qd.econference;

import com.qd.econference.auth.Role;
import com.qd.econference.auth.RoleRepository;
import com.qd.econference.auth.User;
import com.qd.econference.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class InitMongoDBConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initMongoDB(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@localhost").isEmpty()) {
                Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
                Role managerRole = roleRepository.save(Role.builder().name("MANAGER").build());
                Role participantRole = roleRepository.save(Role.builder().name("PARTICIPANT").build());
                String password = passwordEncoder.encode("123");
                userRepository.save(User.builder().id(BigInteger.ONE).email("admin@localhost")
                        .name("Alice").password(password).roles(Set.of(adminRole)).enabled(true).build());
                userRepository.save(User.builder().id(BigInteger.TWO).email("manager@localhost")
                        .name("Bob").password(password).roles(Set.of(managerRole)).enabled(true).build());
                userRepository.save(User.builder().id(BigInteger.TEN).email("participant@localhost")
                        .name("John").password(password).roles(Set.of(participantRole)).enabled(true).build());
            }
        };
    }
}
