package com.qd.econference.domain;

import lombok.Data;

@Data
public class AddRoomRequest {
    private String name;
    private Integer seatCount;
}
