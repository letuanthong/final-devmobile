package com.dev.server.dtos.otp;

import java.time.LocalDateTime;

public record Otp(
        String idOtp,
        String idUser,
        String otpCode,
        Boolean otpIsUsed,
        LocalDateTime expiresAt
        ) {
}
