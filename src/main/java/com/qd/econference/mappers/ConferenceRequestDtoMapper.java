package com.qd.econference.mappers;

import com.qd.econference.domain.AddConferenceRequest;
import com.qd.econference.domain.UpdateConferenceRequest;
import com.qd.econference.dto.AddConferenceRequestDto;
import com.qd.econference.dto.UpdateConferenceRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface ConferenceRequestDtoMapper {
    AddConferenceRequest mapToAddConferenceRequest(AddConferenceRequestDto dto);

    UpdateConferenceRequest mapToUpdateConferenceRequest(UpdateConferenceRequestDto dto);
}
