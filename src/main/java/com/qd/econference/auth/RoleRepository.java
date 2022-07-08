package com.qd.econference.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoleRepository extends MongoRepository<Role, BigInteger> {
    Role findByName(String name);
}
