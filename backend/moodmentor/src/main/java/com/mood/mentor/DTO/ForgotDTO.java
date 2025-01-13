package com.mood.mentor.DTO;

public class ForgotDTO {

    private String email;
    private int OTP;
    private String password;
    private String new_password;

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for OTP
    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for new_password
    public String getNew_password() {
            return new_password;
    }

    // Getter and Setter for new_password
    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }


    }
