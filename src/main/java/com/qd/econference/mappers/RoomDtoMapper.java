package com.qd.econference.mappers;

import com.qd.econference.domain.Room;
import com.qd.econference.dto.RoomDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoomDtoMapper {
    RoomDto mapToRoomDto(Room room);
}
