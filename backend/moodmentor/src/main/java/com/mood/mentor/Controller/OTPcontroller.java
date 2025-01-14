package com.mood.mentor.Controller;

import com.mood.mentor.Services.EmailService;
import com.mood.mentor.Services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        System.out.println("Sending OTP to " + email);

        // Generate OTP
        String otp = otpService.generateOTP();
        System.out.println("Generated OTP: " + otp);

        // Store OTP in memory
        otpService.saveOTP(email, otp);

        // Send OTP via email
        String subject = "Your OTP Code";
        String body = "Dear User,\n\nYour OTP for verification is: " + otp +
                "\n\nThe code is valid for 2 minutes.\n\nThank you!";
        emailService.sendEmail(email, subject, body);

        return "OTP sent to " + email;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        System.out.println("Validating OTP for " + email);
        System.out.println("Received OTP: " + otp);
        boolean isValid = otpService.validateOTP(email, otp);
        if (isValid) {
            otpService.deleteOTP(email);
            return ResponseEntity.ok("Valid OTP!");
        }
        return ResponseEntity.badRequest().body("Invalid OTP!");
    }
}