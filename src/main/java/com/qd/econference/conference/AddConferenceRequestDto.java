package com.qd.econference.conference;

import lombok.Data;

import java.math.BigInteger;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddConferenceRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private Integer expectedParticipantCount;
    @NotNull
    private BigInteger roomId;
}
