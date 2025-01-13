package com.mood.mentor.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private final Random random = new Random();

    // Generate a random 6-digit OTP
    public String generateOTP() {
        return String.format("%06d", random.nextInt(1000000));
    }

    // Save OTP to Redis with a 10-minute expiration
    public void saveOTP(String key, String otp) {
        redisTemplate.opsForValue().set(key, otp, 2, TimeUnit.MINUTES);
    }

    // Validate the OTP
    public boolean validateOTP(String key, String otp) {
        String storedOtp = redisTemplate.opsForValue().get(key);
        return storedOtp != null && storedOtp.equals(otp);
    }

    // Delete OTP after validation
    public void deleteOTP(String key) {
        redisTemplate.delete(key);
    }
}
