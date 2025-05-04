package com.example.dib.model;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;

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
    String userEmail;
    String userPassword;
    String userPhoneNumber;
    String userRole;
    String userPicture;
    String createdAt;
    String updatedAt;
}