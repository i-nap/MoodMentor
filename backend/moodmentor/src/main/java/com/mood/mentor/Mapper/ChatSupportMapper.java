package com.mood.mentor.Mapper;


import com.mood.mentor.Entities.ChatSupportEntities;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatSupportMapper {

    @Select("SELECT chat_id, topic FROM chat_support WHERE user_id = #{userId}")
    public List<String> getTopics(int userId);

    @Select("SELECT *  FROM chat_support WHERE chat_id = #{chatId}")
    public List<ChatSupportEntities> getChat(int chatId);

    @Insert("INSERT INTO chat_support (user_id, topic,chat_id, user_message, support_message, timestamp) " +
            "VALUES (#{userId}, #{chat_id},#{topic}, #{userMessage}, #{supportMessage}, CURRENT_TIMESTAMP)")
    public void saveChat(int userId,int chat_id, String topic, String userMessage, String supportMessage);


}
