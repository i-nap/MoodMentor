package com.mood.mentor.Controller;

import com.mood.mentor.Services.EmailService;
import com.mood.mentor.Services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/otp")
public class OTPcontroller {

    @Autowired
    private OTPService otpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        // Generate OTP
        String otp = otpService.generateOTP();

        // Use email as the key to store OTP in Redis
        otpService.saveOTP(email, otp);

        // Send OTP via email
        String subject = "Your OTP Code";
        String body = "Dear User,\n\nYour OTP for verification is: " + otp +
                "\n\nThe code is valid for 10 minutes.\n\nThank you!";
        emailService.sendEmail(email, subject, body);

        return "OTP sent to " + email;
    }


    @PostMapping("/validate")
    public String validateOTP(@RequestBody Map<String, String> request) {
        // Validate OTP
        String email = request.get("email");
        String otp = request.get("otp");
        boolean isValid = otpService.validateOTP(email, otp);
        if (isValid) {
            // OTP is valid, delete it from Redis
            otpService.deleteOTP(email);
            return "OTP is valid!";
        }
        return "Invalid or expired OTP!";
    }
}
