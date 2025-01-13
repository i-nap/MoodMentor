package com.mood.mentor.Services;

import com.mood.mentor.DTO.LoginRequestDTO;
import com.mood.mentor.DTO.SIgnUpDTO;
import com.mood.mentor.Entities.ProfessionalDetails;
import com.mood.mentor.Mapper.ProfessionalMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ProfessionalService {
  private final ProfessionalMapper ProfessionalMapper;
  private final PasswordEncoder passwordEncoder;

    public ProfessionalService(com.mood.mentor.Mapper.ProfessionalMapper professionalMapper, PasswordEncoder passwordEncoder) {
        ProfessionalMapper = professionalMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> professional_login(LoginRequestDTO loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        ProfessionalDetails professionalDetails = ProfessionalMapper.findByEmail(email);

        if (professionalDetails != null) {
            boolean passwordMatches = passwordEncoder.matches(password, professionalDetails.getPassword());

            if (passwordMatches) {

                Map<String, Object> response = getProfessionalDetails(professionalDetails);
                return ResponseEntity.ok(response);

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Professional not found");
        }
    }
    private static Map<String, Object> getProfessionalDetails(ProfessionalDetails professionalDetails) {
        Map<String,Object> response =   new HashMap<>();
        if (!professionalDetails.getIsActive()) {
            response.put("message", "You are not approved yet");
            return response;
        }
        response.put("message", "Login successful");
        response.put("name", professionalDetails.getFirst_name() + " " + professionalDetails.getLast_name());
        response.put("email", professionalDetails.getEmail());
        response.put("professionalId", professionalDetails.getProfessional_id());
        response.put("role", professionalDetails.getRole());
        response.put("bio", professionalDetails.getBio());
        return response;
    }
    public ResponseEntity<?> professional_register(SIgnUpDTO sIgnUpDTO){
        ProfessionalDetails professionalDetails = new ProfessionalDetails();
        professionalDetails.setEmail(sIgnUpDTO.getEmail());
        professionalDetails.setFirst_name(sIgnUpDTO.getFirstName());
        professionalDetails.setLast_name(sIgnUpDTO.getLastName());
        professionalDetails.setPassword(passwordEncoder.encode(sIgnUpDTO.getPassword()));
        professionalDetails.setDob(sIgnUpDTO.getDateOfBirth());
        professionalDetails.setBio(sIgnUpDTO.getBio());
        professionalDetails.setRole(sIgnUpDTO.getRole());
        professionalDetails.setIsActive(false);

        ProfessionalMapper.insertProfessionalDetails(professionalDetails);
        return ResponseEntity.ok("You'll get a email verification when you are approved");
    }

}
