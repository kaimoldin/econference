package com.qd.econference.repositories;

import com.qd.econference.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, BigInteger> {
    Optional<Role> findByName(String name);
}
