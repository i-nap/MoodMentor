package com.mood.mentor.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChatBotMapper {
    @Select("SELECT user_text, chat_text, date FROM chatbot " +
            "WHERE chat_id = #{chatId} AND user_id = #{userId} " +
            "ORDER BY date DESC LIMIT 50")
    List<Map<String, String>> getChat(int userId, int chatId);

    @Insert("INSERT INTO chatbot (user_text, chat_id, user_id, date) " +
            "VALUES (#{userText}, #{chatId}, #{userId}, CURRENT_TIMESTAMP)")
    void saveUserChat(String userText, int userId, int chatId);

    @Insert("INSERT INTO chatbot (chat_text, chat_id, user_id, date) " +
            "VALUES (#{chatText}, #{chatId}, #{userId}, CURRENT_TIMESTAMP)")
    void saveChat(String chatText, int userId, int chatId);
}
