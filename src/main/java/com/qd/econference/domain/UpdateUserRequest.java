package com.qd.econference.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    private String name;
    private String password;
}
