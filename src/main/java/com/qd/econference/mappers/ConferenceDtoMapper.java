package com.qd.econference.mappers;

import com.qd.econference.domain.Conference;
import com.qd.econference.dto.ConferenceDto;
import org.mapstruct.Mapper;

@Mapper(uses = {RoomDtoMapper.class})
public interface ConferenceDtoMapper {
    ConferenceDto mapToConferenceDto(Conference conference);
}
