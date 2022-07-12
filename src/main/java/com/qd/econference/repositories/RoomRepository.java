package com.qd.econference.repositories;

import com.qd.econference.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface RoomRepository extends MongoRepository<Room, BigInteger> {
}
