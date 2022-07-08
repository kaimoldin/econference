package com.qd.econference.conference;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferenceStatistics {
    private Integer expectedParticipantCount;
    private Integer actualParticipantCount;
    private Integer availableSeatCount;
}
