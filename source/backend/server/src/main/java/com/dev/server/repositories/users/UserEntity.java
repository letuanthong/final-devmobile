package com.dev.server.repositories.users;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class UserEntity {
    @Id
    @Column(name = "user_id")
    String idUser;

    @Column(name = "user_full_name")
    String userFullName;

    @Column(name = "user_gender")
    String userGender;

    @Column(name = "user_date_of_birth")
    Date userDateOfBirth;

    @Column(name = "user_identity_number")
    String userIdentityNumber;

    @Column(name = "user_identity_issued_date")
    Date userIdentityIssuedDate;

    @Column(name = "user_identity_expires_date")
    Date userIdentityExpiresDate;

    @Column(name = "user_identity_issued_place")
    String userIdentityIssuedPlace;

    @Column(name = "user_identity_expires_place")
    String userIdentityExpiresPlace;

    @Column(name = "user_place_of_origin")
    String userPlaceOfOrigin;

    @Column(name = "user_place_of_residence")
    String userPlaceOfResidence;

    @Column(name = "user_email", unique = true)
    String userEmail;

    @Column(name = "user_password", nullable = false)
    String userPassword;

    @Column(name = "user_phone_number", unique = true, nullable = false, length = 20)
    String userPhoneNumber;

    @Column(name = "user_role")
    String userRole;

    @Column(name = "user_picture")
    String userPicture;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
