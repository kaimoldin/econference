package com.qd.econference;

import com.qd.econference.auth.RoleRepository;
import com.qd.econference.auth.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetMongoDBConfig {
    @Bean
    public CommandLineRunner resetMongoDB(RoleRepository roleRepository, UserRepository userRepository) {
        return (args) -> {
            roleRepository.deleteAll();
            userRepository.deleteAll();
        };
    }
}
