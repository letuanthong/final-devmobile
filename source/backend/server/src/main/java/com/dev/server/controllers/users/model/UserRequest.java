package com.dev.server.controllers.users.model;

import lombok.Builder;

import java.sql.Date;

@Builder
public record UserRequest(
        String idUser,
        String userFullName,
        String userGender,
        String userDateOfBirth,
        String userIdentityNumber,
        String userIdentityIssuedDate,
        String userIdentityExpiresDate,
        String userIdentityIssuedPlace,
        String userIdentityExpiresPlace,
        String userPlaceOfOrigin,
        String userPlaceOfResidence,
        String userEmail,
        String userPassword,
        String userPhoneNumber,
        String userRole
) {
}
