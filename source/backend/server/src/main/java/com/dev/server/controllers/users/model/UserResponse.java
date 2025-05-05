package com.dev.server.controllers.users.model;

import lombok.Builder;

import java.sql.Date;
import java.time.LocalDateTime;

@Builder
public record UserResponse(
        String idUser,
        String userFullName,
        String userGender,
        Date userDateOfBirth,
        String userIdentityNumber,
        Date userIdentityIssuedDate,
        Date userIdentityExpiresDate,
        String userIdentityIssuedPlace,
        String userIdentityExpiresPlace,
        String userPlaceOfOrigin,
        String userPlaceOfResidence,
        String userEmail,
        String userPassword,
        String userPhoneNumber,
        String userRole,
        String userPicture,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
