package com.qd.econference;

import com.qd.econference.auth.Role;
import com.qd.econference.auth.RoleRepository;
import com.qd.econference.auth.User;
import com.qd.econference.auth.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class InitMongoDBConfig {
    @Bean
    public CommandLineRunner initMongoDB(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@localhost").isEmpty()) {
                Role adminRole = roleRepository.save(Role.builder().name("admin").build());
                Role managerRole = roleRepository.save(Role.builder().name("manager").build());
                Role participantRole = roleRepository.save(Role.builder().name("participant").build());
                userRepository.save(User.builder().email("admin@localhost").name("Alice").password("123").roles(Set.of(adminRole)).build());
                userRepository.save(User.builder().email("manager@localhost").name("Bob").password("123").roles(Set.of(managerRole)).build());
                userRepository.save(User.builder().email("participant@localhost").name("John").password("123").roles(Set.of(participantRole)).build());
            }
        };
    }
}
