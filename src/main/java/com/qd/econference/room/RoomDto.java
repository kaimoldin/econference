package com.qd.econference.room;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RoomDto {
    private BigInteger id;
    private String name;
    private Integer seatCount;
    private Boolean enabled;
}
