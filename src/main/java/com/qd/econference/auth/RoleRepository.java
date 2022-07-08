package com.qd.econference.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, BigInteger> {
    Optional<Role> findByName(String name);
}
