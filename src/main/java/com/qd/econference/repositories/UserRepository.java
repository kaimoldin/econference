package com.qd.econference.repositories;

import com.qd.econference.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, BigInteger> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
