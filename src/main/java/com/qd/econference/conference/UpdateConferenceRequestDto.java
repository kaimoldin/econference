package com.qd.econference.conference;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UpdateConferenceRequestDto {
    private String name;
    private Integer expectedParticipantCount;
    private BigInteger roomId;
    private Boolean enabled;
}
