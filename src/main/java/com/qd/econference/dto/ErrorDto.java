package com.qd.econference.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ErrorDto {
    private UUID requestId;
    private String message;
}
