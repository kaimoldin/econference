package com.qd.econference.users;


import lombok.Builder;
import lombok.Data;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddUserRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;
}
