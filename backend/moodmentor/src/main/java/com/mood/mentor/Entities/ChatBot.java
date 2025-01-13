package com.mood.mentor.Entities;

import java.time.LocalDateTime;

public class ChatBot {
    private Long chat_id;       // primary key and auto increment
    private Long user_id;       // foreign key
    private String bot_response;
    private String user_message;
    private LocalDateTime time_of_message;
    private Long session_id;    // primary key

    // Getters and Setters

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getBot_response() {
        return bot_response;
    }

    public void setBot_response(String bot_response) {
        this.bot_response = bot_response;
    }

    public String getUser_message() {
        return user_message;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }

    public LocalDateTime getTime_of_message() {
        return time_of_message;
    }

    public void setTime_of_message(LocalDateTime time_of_message) {
        this.time_of_message = time_of_message;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }
}
