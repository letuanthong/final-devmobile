package com.dev.server.controllers.users.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        String idUser,
        String userFullName,
        String userEmail,
        String userPassword,
        String userPhoneNumber,
        String userRole,
        String userPicture,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
