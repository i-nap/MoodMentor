package com.mood.mentor.DTO;

import java.util.Date;

public class SIgnUpDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isMentor;
    private Date dateOfBirth;
    private String EmergencyContact;
    private String Gender;
    private int Age;
    private String bio;
    private String role;

    // Getter and Setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for isMentor
    public Boolean getIsMentor() {
        return isMentor;
    }

    public void setIsMentor(Boolean isMentor) {
        this.isMentor = isMentor;
    }

    // Getter and Setter for dateOfBirth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Getter and Setter for EmergencyContact
    public String getEmergencyContact() {
        return EmergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        EmergencyContact = emergencyContact;
    }

    // Getter and Setter for Gender
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    // Getter and Setter for Age
    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    // Getter and Setter for bio
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
