package com.mood.mentor.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckMapper {

    @Insert("Insert into check_table (val) values (#{val})")
    void insertVal(String val);

    @Select("SELECT val FROM check_table")
    String getVal();
}
