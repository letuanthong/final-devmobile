package com.dev.server.services.otp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    Map<String, String> otpMap = new HashMap<>();

    private String generateOtp(String userPhoneNumber) {
        Random random = new Random();
        String otp = String.valueOf(random.nextInt(900000) + 100000);
        otpMap.put(userPhoneNumber, otp);
        return otp;
    }

    public void sendOtp(String userPhoneNumber) {
        if (!userPhoneNumber.startsWith("+")) {
            userPhoneNumber = "+84" + userPhoneNumber.substring(1); // Thêm mã quốc gia Việt Nam
        }
        String otp = "Your OTP: "+ generateOtp(userPhoneNumber);
        Twilio.init(accountSid,authToken);
        Message.creator(new PhoneNumber(userPhoneNumber), new PhoneNumber("+19785915496"),otp).create();
    }

    public boolean checkOtp(String userPhoneNumber, String otp) {
        if (otpMap.containsKey(userPhoneNumber)) {
            if(otp.equals(otpMap.get(userPhoneNumber)))
            {
                return true;
            };
        }
        return false;
    }


}
