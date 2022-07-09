package com.qd.econference.conference;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class UpdateConferenceRequest {
    private String name;
    private Integer expectedParticipantCount;
    private BigInteger roomId;
    private Boolean enabled;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
