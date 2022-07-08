package com.qd.econference.room;

import org.mapstruct.Mapper;

@Mapper
public interface RoomDtoMapper {
    RoomDto mapToRoomDto(Room room);
}
