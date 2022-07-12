package com.qd.econference.domain;


import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AddUserRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;
}
