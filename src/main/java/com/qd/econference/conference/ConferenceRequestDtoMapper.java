package com.qd.econference.conference;

import org.mapstruct.Mapper;

@Mapper
public interface ConferenceRequestDtoMapper {
    AddConferenceRequest mapToAddConferenceRequest(AddConferenceRequestDto dto);

    UpdateConferenceRequest mapToUpdateConferenceRequest(UpdateConferenceRequestDto dto);
}
