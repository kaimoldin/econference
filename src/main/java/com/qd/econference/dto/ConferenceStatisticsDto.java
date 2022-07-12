package com.qd.econference.dto;

import lombok.Data;

@Data
public class ConferenceStatisticsDto {
    private Integer expectedParticipantCount;
    private Integer actualParticipantCount;
    private Integer availableSeatCount;
}
