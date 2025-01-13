package com.mood.mentor.Services;

import com.mood.mentor.DTO.ForgotDTO;
import com.mood.mentor.DTO.LoginRequestDTO;
import com.mood.mentor.DTO.SIgnUpDTO;
import com.mood.mentor.Entities.UserDetails;
import com.mood.mentor.Mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
        private final UserMapper UserMapper;
        private final PasswordEncoder passwordEncoder;
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        UserMapper = userMapper;
        this.passwordEncoder = passwordEncoder;

    }

    public ResponseEntity<?> user_login(LoginRequestDTO loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UserDetails userDetails = UserMapper.findByEmail(email);
        if (userDetails != null) {
            boolean passwordMatches = passwordEncoder.matches(password, userDetails.getPassword());
            if (passwordMatches) {
                Map<String, Object> response = getUserDetails(userDetails);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

    }
    private static Map<String,Object> getUserDetails(UserDetails userDetails) {
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("name", userDetails.getFirstName() + " " + userDetails.getLastName());
        response.put("email", userDetails.getEmail());
        response.put("userId", userDetails.getUser_id());

        return response;
    }
    public ResponseEntity<?> user_register(SIgnUpDTO sIgnUpDTO) {
        UserDetails userDetails = new UserDetails();
        String password = passwordEncoder.encode(sIgnUpDTO.getPassword());
        userDetails.setEmail(sIgnUpDTO.getEmail());
        userDetails.setFirstName(sIgnUpDTO.getFirstName());
        userDetails.setLastName(sIgnUpDTO.getLastName());
        userDetails.setPassword(password);
        userDetails.setDob(sIgnUpDTO.getDateOfBirth());
        userDetails.setEmergency(sIgnUpDTO.getEmergencyContact());
        userDetails.setGender(sIgnUpDTO.getGender());
        userDetails.setAge(sIgnUpDTO.getAge());
        UserMapper.insertUser(userDetails);


        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<?> forgot(ForgotDTO forgotDTO){
        UserDetails userDetails = UserMapper.findByEmail(forgotDTO.getEmail());
        if(userDetails != null){
            //send email to the user

            return ResponseEntity.ok("Email sent to your email address");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");

    }
    public ResponseEntity<?> passwordChange(ForgotDTO forgotDTO){
        UserDetails userDetails = UserMapper.findByEmail(forgotDTO.getEmail());
        if (userDetails != null) {
            String purano_password = forgotDTO.getPassword();
            String naya_password = forgotDTO.getNew_password();
            String db_encoded_password = UserMapper.findByEmail(forgotDTO.getEmail()).getPassword();
            boolean passwordMatches = passwordEncoder.matches(purano_password, db_encoded_password);
            if (passwordMatches) {
                userDetails.setPassword(passwordEncoder.encode(naya_password));
                UserMapper.updatePassword(userDetails);
                return ResponseEntity.ok("Password changed successfully");
            }
            else {
            return ResponseEntity.badRequest().body("Password does not match");
            }
            }
        else {
            return ResponseEntity.badRequest().body("User not found");
        }

    }



}
