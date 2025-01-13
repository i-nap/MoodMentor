package com.mood.mentor.Controller;

import com.mood.mentor.Entities.UserDetails;
import com.mood.mentor.Mapper.CheckMapper;
import com.mood.mentor.Mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserMapper userMapper;
    private final UserDetails userDetails;
    private final CheckMapper checkMapper;
    public UserController(UserMapper userMapper, UserDetails userDetails, CheckMapper checkMapper) {
        this.userMapper = userMapper;
        this.userDetails = userDetails;
        this.checkMapper = checkMapper;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(@AuthenticationPrincipal OAuth2User principal) {
        try {
            userDetails.setEmail(principal.getAttribute("email"));
            userDetails.setFirstName(principal.getAttribute("given_name"));
            userDetails.setLastName(principal.getAttribute("family_name"));
            userDetails.setGender(principal.getAttribute("gender"));
            userDetails.setAge(principal.getAttribute("age") != null ? Integer.parseInt(principal.getAttribute("age")) : 0);
            userDetails.setDob(principal.getAttribute("dob"));
            userDetails.setEmergency(principal.getAttribute("emergency"));
            userDetails.setOTP(0);

            userMapper.insertUser(userDetails);

            logger.info("User details inserted successfully for email: {}", userDetails.getEmail());
            return ResponseEntity.ok("User details successfully inserted.");
        } catch (Exception e) {
            logger.error("Error inserting user details", e);
            return ResponseEntity.status(500).body("Error inserting user details.");
        }
    }

    @PostMapping ("/updateDetails")
public ResponseEntity<?> updateDetials (UserDetails userDetails) {
        try {
            userMapper.updateDetails(userDetails);
            return ResponseEntity.ok("Details updated  successfully");
        } catch (Exception e) {
            logger.error("Error updating details", e);
            return ResponseEntity.status(500).body("Error updating password");
        }
    }

    }




