package com.dev.server.controllers.otp;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.services.otp.OtpUseCaseService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpController implements OtpAPI{
    @NonNull OtpUseCaseService otpUseCaseService;

    @Override
    public ValueResponse<String> getOTP(String userPhoneNumber) {
        otpUseCaseService.sendOtp(userPhoneNumber);
        return ValueResponse.<String>builder()
                .code(200)
                .message("Get OTP successfully")
                .data("Get OTP successfully")
                .build();
    }

    @Override
    public ValueResponse<String> verifyOTP(String userPhoneNumber, String otp) {
        if(otpUseCaseService.checkOtp(userPhoneNumber, otp)){
            return ValueResponse.<String>builder()
                    .code(200)
                    .message("Verify OTP successfully")
                    .data("Verify OTP successfully")
                    .build();
        } else {
            return ValueResponse.<String>builder()
                    .code(400)
                    .message("Verify OTP failed")
                    .data("Verify OTP failed")
                    .build();
        }

    }
}
