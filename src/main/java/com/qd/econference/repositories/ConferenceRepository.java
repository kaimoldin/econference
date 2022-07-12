package com.qd.econference.repositories;

import com.qd.econference.domain.Conference;
import com.qd.econference.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

public interface ConferenceRepository extends MongoRepository<Conference, BigInteger> {
    Set<Conference> findAllByParticipantsContainingAndEnabledIsTrue(User user);

    Set<Conference> findAllByParticipantsNotContainingAndEnabledIsTrue(User user);

    @Query("{'room.$id': ?0, 'startTime':  {$lt: ?2}, 'endTime': {$gt: ?1}}")
    Set<Conference> findConferences(BigInteger roomId, LocalDateTime startTime, LocalDateTime endTime);
}
