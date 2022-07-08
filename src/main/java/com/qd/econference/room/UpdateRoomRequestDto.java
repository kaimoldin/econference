package com.qd.econference.room;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateRoomRequestDto {
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    @Max(1000)
    private Integer seatCount;
}
