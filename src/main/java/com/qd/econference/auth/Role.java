package com.qd.econference.auth;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigInteger;

@Data
@Builder
@Document(collection = "roles")
public class Role implements GrantedAuthority {
    @Id
    private BigInteger id;
    @Indexed(unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
