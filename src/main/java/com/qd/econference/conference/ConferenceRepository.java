package com.qd.econference.conference;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ConferenceRepository extends MongoRepository<Conference, BigInteger> {
}
