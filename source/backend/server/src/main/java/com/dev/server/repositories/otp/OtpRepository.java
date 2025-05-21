package com.dev.server.repositories.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, String> {
    OtpEntity findByIdUser(String idUser);
    OtpEntity findByOtpCode(String otpCode);
}
