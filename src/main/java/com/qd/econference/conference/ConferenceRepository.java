package com.qd.econference.conference;

import com.qd.econference.auth.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Set;

@Repository
public interface ConferenceRepository extends MongoRepository<Conference, BigInteger> {
    Set<Conference> findAllByParticipantsContainingAndEnabledIsTrue(User user);

    Set<Conference> findAllByParticipantsNotContainingAndEnabledIsTrue(User user);
}
