package com.qd.econference.conference;

import com.qd.econference.room.RoomDto;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class ConferenceDto {
    private BigInteger id;
    private String name;
    private Integer expectedParticipantCount;
    private RoomDto room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean enabled;
}
