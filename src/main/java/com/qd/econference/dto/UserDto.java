package com.qd.econference.dto;


import com.qd.econference.domain.Role;
import lombok.Data;

import java.math.BigInteger;
import java.util.Set;

@Data
public class UserDto {

    private BigInteger id;
    private String name;
    private String email;
    private Boolean enabled;
    private Set<Role> roles;
}
