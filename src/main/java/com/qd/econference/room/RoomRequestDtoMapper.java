package com.qd.econference.room;

import org.mapstruct.Mapper;

@Mapper
public interface RoomRequestDtoMapper {
    AddRoomRequest mapToAddRoomRequest(AddRoomRequestDto dto);

    UpdateRoomRequest mapToUpdateRoomRequest(UpdateRoomRequestDto dto);
}
