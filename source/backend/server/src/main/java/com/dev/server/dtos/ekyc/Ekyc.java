package com.dev.server.dtos.ekyc;

import java.time.LocalDateTime;

public record Ekyc(
        String idEkyc,
        String idUser,
        String ekycImageUrl,
        Boolean ekycVerified,
        LocalDateTime ekycVerifiedAt
) {
}
