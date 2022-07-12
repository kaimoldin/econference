package com.qd.econference.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddConferenceRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private Integer expectedParticipantCount;
    @NotNull
    private BigInteger roomId;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}
