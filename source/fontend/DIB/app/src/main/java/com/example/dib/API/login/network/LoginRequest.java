package com.example.dib.API.login.network;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class LoginRequest {
    String userPhoneNumber;
    String userPassword;
}
