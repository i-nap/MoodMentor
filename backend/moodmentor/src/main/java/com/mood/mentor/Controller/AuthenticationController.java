package com.mood.mentor.Controller;

import com.mood.mentor.DTO.ForgotDTO;
import com.mood.mentor.DTO.LoginRequestDTO;
import com.mood.mentor.DTO.SIgnUpDTO;
import com.mood.mentor.Services.ProfessionalService;
import com.mood.mentor.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final ProfessionalService professionalService;

@Autowired
    public AuthenticationController(UserService userService, ProfessionalService professionalService) {
        this.userService = userService;
        this.professionalService = professionalService;

    }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest, @RequestHeader("Referer") String refererType) {
            return userService.user_login(loginRequest);


    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SIgnUpDTO signuprequest) {
    System.out.println(signuprequest); // Debugging log
        System.out.println("Registering user: " + signuprequest.getEmail()); // Debugging log


            return userService.user_register(signuprequest);

    }


    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody ForgotDTO forgotDTO) {
        return userService.forgot(forgotDTO);
    }

    @PostMapping("/passwordChange")
    public ResponseEntity<?> reset(@RequestBody ForgotDTO forgotDTO) {

        return userService.passwordChange(forgotDTO);
    }

}




