package com.dev.server.dtos.otp;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Otp(
        String idOtp,
        String idUser,
        String otpCode,
        Boolean otpIsUsed,
        LocalDateTime expiresAt
        ) {
}
