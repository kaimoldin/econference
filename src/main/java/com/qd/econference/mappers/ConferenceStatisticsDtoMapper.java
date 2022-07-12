package com.qd.econference.mappers;

import com.qd.econference.domain.ConferenceStatistics;
import com.qd.econference.dto.ConferenceStatisticsDto;
import org.mapstruct.Mapper;

@Mapper
public interface ConferenceStatisticsDtoMapper {
    ConferenceStatisticsDto mapToConferenceStatisticsDto(ConferenceStatistics statistics);
}
