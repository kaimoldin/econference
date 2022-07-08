package com.qd.econference.conference;

import com.qd.econference.room.RoomDtoMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {RoomDtoMapper.class})
public interface ConferenceDtoMapper {
    ConferenceDto mapToConferenceDto(Conference conference);
}
