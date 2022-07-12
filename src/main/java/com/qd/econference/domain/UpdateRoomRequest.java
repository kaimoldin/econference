package com.qd.econference.domain;

import lombok.Data;

@Data
public class UpdateRoomRequest {
    private String name;
    private Integer seatCount;
    private Boolean enabled;
}
