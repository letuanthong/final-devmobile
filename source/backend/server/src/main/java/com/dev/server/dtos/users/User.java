package com.dev.server.dtos.users;
import java.time.LocalDateTime;

public record User(
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
