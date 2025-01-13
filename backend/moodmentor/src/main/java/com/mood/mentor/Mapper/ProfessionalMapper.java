package com.mood.mentor.Mapper;

import com.mood.mentor.Entities.ProfessionalDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProfessionalMapper {
    @Select("SELECT email, password FROM ProfessionalDetails WHERE email = #{email}")
    ProfessionalDetails findByEmail(String email);

    @Insert("INSERT INTO ProfessionalDetails (email, first_name, last_name, password, dob, bio, role) " +
            "VALUES (#{email}, #{firstName}, #{lastName}, #{password}, #{dob}, #{bio}, #{role})")
    void insertProfessionalDetails(ProfessionalDetails professionalDetails);

    @Update("UPDATE ProfessionalDetails SET bio = #{bio} role = #{role} WHERE email = #{email}")
    boolean updateProfessionalDetails(ProfessionalDetails professionalDetails);
}

