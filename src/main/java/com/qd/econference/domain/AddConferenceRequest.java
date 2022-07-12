package com.qd.econference.domain;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class AddConferenceRequest {
    private String name;
    private Integer expectedParticipantCount;
    private BigInteger roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
