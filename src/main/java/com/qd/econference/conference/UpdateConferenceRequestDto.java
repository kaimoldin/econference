package com.qd.econference.conference;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
public class UpdateConferenceRequestDto {
    private String name;
    private Integer expectedParticipantCount;
    private BigInteger roomId;
    private Boolean enabled;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
