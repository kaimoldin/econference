package com.qd.econference.users;


import com.qd.econference.auth.Role;
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
