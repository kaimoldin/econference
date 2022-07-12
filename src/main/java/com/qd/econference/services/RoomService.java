package com.qd.econference.services;

import com.qd.econference.domain.AddRoomRequest;
import com.qd.econference.domain.Room;
import com.qd.econference.domain.UpdateRoomRequest;
import com.qd.econference.exceptions.RoomNotFoundException;
import com.qd.econference.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room getById(BigInteger id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room add(AddRoomRequest request) {
        return roomRepository.save(Room.builder().name(request.getName()).seatCount(request.getSeatCount()).build());
    }

    public Room update(BigInteger roomId, UpdateRoomRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));
        if (Strings.isNotBlank(request.getName())) {
            room.setName(request.getName());
        }
        if (request.getSeatCount() != null) {
            room.setSeatCount(request.getSeatCount());
        }
        if (request.getEnabled() != null) {
            room.setEnabled(request.getEnabled());
        }
        return roomRepository.save(room);
    }
}
