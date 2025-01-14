package com.mood.mentor.Services;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;

@Service
public class OTPService {
    // Store OTP with expiration time
    private final Map<String, OTPEntry> otpMap = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private static final long OTP_VALID_DURATION = 2; // 2 minutes

    // Internal class to store OTP and its expiration time
    private static class OTPEntry {
        String otp;
        LocalDateTime expiryTime;

        OTPEntry(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }

    // Generate a random 6-digit OTP
    public String generateOTP() {
        return String.format("%06d", random.nextInt(1000000));
    }

    // Save OTP with expiration time
    public void saveOTP(String key, String otp) {
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(OTP_VALID_DURATION);
        otpMap.put(key, new OTPEntry(otp, expiryTime));
    }

    // Validate the OTP
    public boolean validateOTP(String key, String otp) {
        OTPEntry entry = otpMap.get(key);
        if (entry == null) {
            return false;
        }

        // Check if OTP has expired
        if (LocalDateTime.now().isAfter(entry.expiryTime)) {
            otpMap.remove(key);
            return false;
        }

        return entry.otp.equals(otp);
    }

    // Delete OTP
    public void deleteOTP(String key) {
        otpMap.remove(key);
    }

    // Optional: Method to clean up expired OTPs
    public void cleanupExpiredOTPs() {
        LocalDateTime now = LocalDateTime.now();
        otpMap.entrySet().removeIf(entry -> now.isAfter(entry.getValue().expiryTime));
    }
}