package com.qd.econference.conference;

import org.mapstruct.Mapper;

@Mapper
public interface ConferenceStatisticsDtoMapper {
    ConferenceStatisticsDto mapToConferenceStatisticsDto(ConferenceStatistics statistics);
}
