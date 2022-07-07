package com.qd.econference.signup;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class SignupRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
}
