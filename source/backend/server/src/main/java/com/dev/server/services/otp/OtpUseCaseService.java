package com.dev.server.services.otp;

import com.dev.server.dtos.otp.Otp;
import com.dev.server.services.users.UserUseCaseService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OtpUseCaseService {
    @NonFinal
    @Value("${twilio.account-sid}")
    private String accountSid;

    @NonFinal
    @Value("${twilio.auth-token}")
    private String authToken;

    @NonNull OtpCommandService otpCommandService;
    @NonNull OtpQueryService otpQueryService;
    @NonNull UserUseCaseService userUseCaseService;

    private String formatPhoneNumber(String phoneNumber) {
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+84" + phoneNumber.substring(1); // Thêm mã quốc gia Việt Nam
        }
        return phoneNumber;
    }

    private String generateOtp(String userPhoneNumber) {
        Random random = new Random();
        String otp = String.valueOf(random.nextInt(900000) + 100000);
        Otp otpEntity = Otp.builder()
                .idOtp(UUID.randomUUID().toString())
                .otpCode(otp)
                .otpIsUsed(false)
                .idUser(userUseCaseService.findByPhoneNumber(userPhoneNumber).idUser())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();
        otpCommandService.save(otpEntity);
        return otp;
    }

    public void sendOtp(String userPhoneNumber) {
        String otp = "Your OTP: "+ generateOtp(userPhoneNumber);
        Twilio.init(accountSid,authToken);
        Message.creator(new PhoneNumber(formatPhoneNumber(userPhoneNumber)), new PhoneNumber("+19785915496"),otp).create();
    }

    public boolean checkOtp(String userPhoneNumber, String otp) {
        String id = userUseCaseService.findByPhoneNumber(userPhoneNumber).idUser();
        String idCheck = otpQueryService.findByOtpCode(otp).idUser();
        return Objects.equals(id, idCheck);
    }


}
