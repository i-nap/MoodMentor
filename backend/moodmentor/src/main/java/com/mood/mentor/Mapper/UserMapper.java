package com.mood.mentor.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.mood.mentor.Entities.UserDetails;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM UserDetails WHERE email = #{email}")
    UserDetails findByEmail(String email);

    @Insert("INSERT INTO userdetails (firstName, lastName, email, password, dob, emergency, gender, age) " +
            "VALUES (#{firstName}, #{lastName}, #{email}, #{password}, #{dob}, #{emergency}, #{gender}, #{age})")
    void insertUser(UserDetails userDetails);


    @Update("UPDATE UserDetails SET password = #{password} WHERE email = #{email}")
    void updatePassword(UserDetails userDetails);

    @Update ("UPDATE UserDetails SET OTP = #{OTP} WHERE email = #{email}")
    void setOTP(UserDetails userDetails);

    @Update("UPDATE UserDetails SET firstName = #{firstName}, lastName = #{lastName},gender = #{gender}  emergency = #{emergency} where email = #{email}")
    void updateDetails(UserDetails userDetails);
}

