package com.example.dib.API.admin.network;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class AdminRequest {
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
}
