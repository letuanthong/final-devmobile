package com.example.dib.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = PRIVATE)
public class User {
    String idUser;
    String userFullName;
    String userGender;
    String userDateOfBirth;
    String userIdentityNumber;
    String userIdentityIssuedDate;
    String userIdentityExpiresDate;
    String userIdentityIssuedPlace;
    String userIdentityExpiresPlace;
    String userPlaceOfOrigin;
    String userPlaceOfResidence;
    String userEmail;
    String userPassword;
    String userPhoneNumber;
    String userRole;
    String userPicture;
    String createdAt;
    String updatedAt;
}