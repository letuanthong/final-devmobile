package com.dev.server.services.otp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OtpUseCaseService {
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
        // Generate OTP
        String otp = generateOtp(userPhoneNumber);
        // Chuẩn hóa số điện thoại
        if (!userPhoneNumber.startsWith("+")) {
            userPhoneNumber = "84" + userPhoneNumber.substring(1); // Thêm mã quốc gia Việt Nam
        }

        // API URL with query parameters
        String url = "https://api.1s2u.io/bulksms?username=letletudi4vv2025&password=sVX5HL6U" +
                "&mno=" + userPhoneNumber +
                "&sid=thong&msg=Your OTP is: " + otp +
                "&mt=0";

        // RestTemplate setup
        RestTemplate restTemplate = new RestTemplate();

        // Send GET request
        try {
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("OTP sent successfully to " + userPhoneNumber + ": " + response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending OTP: " + e.getMessage());
        }
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
