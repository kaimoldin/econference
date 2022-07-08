package com.qd.econference.conference;

import com.qd.econference.room.RoomDto;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ConferenceStatisticsDto {
    private Integer expectedParticipantCount;
    private Integer actualParticipantCount;
    private Integer availableSeatCount;
}
