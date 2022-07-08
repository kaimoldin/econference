package com.qd.econference.room;

import lombok.Data;

@Data
public class AddRoomRequest {
    private String name;
    private Integer seatCount;
}
