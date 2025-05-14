package com.dev.server.services.otp;

import com.dev.server.dtos.otp.OtpMapper;
import com.dev.server.repositories.otp.OtpRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OtpQueryService {
    @NonNull OtpRepository otpRepository;
    @NonNull OtpMapper otpMapper;
}
