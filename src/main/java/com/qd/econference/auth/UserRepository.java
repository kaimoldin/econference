package com.qd.econference.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, BigInteger> {
    public Optional<User> findByEmail(String email);
}
