package com.mood.mentor.Services;

import com.mood.mentor.Mapper.ChatSupportMapper;
import com.mood.mentor.Mapper.CheckMapper;
import org.springframework.stereotype.Service;

@Service
public class ChatSupport {
    private ChatSupportMapper chatSupportMapper;
    public String sendChat(int user_id,int chat_id ,String topic , String user_message) {
        String support_message = "";
        chatSupportMapper.saveChat(user_id, chat_id,topic, user_message, support_message);
        // API ma message pathaune aba yo ani esko return k aauxa tyo anusar message pathuane
        support_message = "Hello, I am here to help you. How can I help you?";
        user_message = "";
        chatSupportMapper.saveChat(user_id, chat_id,topic, user_message, support_message);
        return support_message;
        }

}
