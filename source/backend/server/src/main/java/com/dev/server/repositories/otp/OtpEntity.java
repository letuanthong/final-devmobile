package com.dev.server.repositories.otp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "otp_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class OtpEntity {
    @Id
    @Column(name = "otp_id")
    String idOtp;

    @Column(name = "user_id")
    String idUser;

    @Column(name = "otp_code")
    String otpCode;

    @Column(name = "otp_is_used")
    Boolean otpIsUsed;

    @Column(name = "expires_at")
    LocalDateTime expiresAt;
}
