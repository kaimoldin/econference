package com.qd.econference;

import com.qd.econference.auth.Role;
import com.qd.econference.auth.RoleRepository;
import com.qd.econference.auth.User;
import com.qd.econference.auth.UserRepository;
import com.qd.econference.conference.Conference;
import com.qd.econference.conference.ConferenceRepository;
import com.qd.econference.room.Room;
import com.qd.econference.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class InitMongoDBConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initMongoDB(RoleRepository roleRepository, UserRepository userRepository,
                                         RoomRepository roomRepository, ConferenceRepository conferenceRepository) {
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
                Room room100 = roomRepository.save(Room.builder().id(BigInteger.valueOf(100))
                        .name("Room 100").seatCount(15).enabled(true).build());
                Room room200 = roomRepository.save(Room.builder().id(BigInteger.valueOf(200))
                        .name("Room 200").seatCount(150).enabled(true).build());
                conferenceRepository.save(Conference.builder().id(BigInteger.valueOf(1000))
                        .name("Summer conference")
                        .room(room100)
                        .startTime(LocalDateTime.of(2022, 6, 1, 9, 0))
                        .endTime(LocalDateTime.of(2022, 8, 31, 18, 0))
                        .expectedParticipantCount(10)
                        .build());
                conferenceRepository.save(Conference.builder().id(BigInteger.valueOf(2000))
                        .name("Winter conference")
                        .room(room200)
                        .startTime(LocalDateTime.of(2021, 12, 1, 9, 0))
                        .endTime(LocalDateTime.of(2022, 2, 28, 18, 0))
                        .expectedParticipantCount(100)
                        .build());
            }
        };
    }
}
