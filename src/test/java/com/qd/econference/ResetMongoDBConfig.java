package com.qd.econference;

import com.qd.econference.auth.RoleRepository;
import com.qd.econference.auth.UserRepository;
import com.qd.econference.conference.ConferenceRepository;
import com.qd.econference.room.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetMongoDBConfig {
    @Bean
    public CommandLineRunner resetMongoDB(RoleRepository roleRepository, UserRepository userRepository,
                                          RoomRepository roomRepository, ConferenceRepository conferenceRepository) {
        return (args) -> {
            conferenceRepository.deleteAll();
            roomRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
        };
    }
}
