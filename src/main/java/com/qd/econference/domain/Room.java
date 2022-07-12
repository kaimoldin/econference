package com.qd.econference.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@Builder
@Document(collection = "rooms")
public class Room {
    @Id
    private BigInteger id;
    private String name;
    private Integer seatCount;
    private Boolean enabled;
}
