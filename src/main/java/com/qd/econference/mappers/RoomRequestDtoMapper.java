package com.qd.econference.mappers;

import com.qd.econference.domain.AddRoomRequest;
import com.qd.econference.domain.UpdateRoomRequest;
import com.qd.econference.dto.AddRoomRequestDto;
import com.qd.econference.dto.UpdateRoomRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoomRequestDtoMapper {
    AddRoomRequest mapToAddRoomRequest(AddRoomRequestDto dto);

    UpdateRoomRequest mapToUpdateRoomRequest(UpdateRoomRequestDto dto);
}
