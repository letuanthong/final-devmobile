package com.dev.server.dtos.users;

import jakarta.persistence.Column;

import java.sql.Date;
import java.time.LocalDateTime;

public record User(
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
