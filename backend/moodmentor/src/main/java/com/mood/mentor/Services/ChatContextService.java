package com.mood.mentor.Services;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatContextService {

    private final List<Map<String, String>> chatHistory = new ArrayList<>();

    public void initializeChat() {
        if (chatHistory.isEmpty()) {
            chatHistory.add(Map.of("role", "system",
                    "content", "You are a mental health assistant specializing in basic mental health evaluations. Your goal is to have a supportive conversation, ask relevant questions based on previous responses, and assess the user for potential conditions like depression, anxiety, or stress. Provide a simple rating for each condition on a scale of 1-10 and adapt your questions based on the user's responses."));
        }
    }

    public void addMessage(String role, String content) {
        chatHistory.add(Map.of("role", role, "content", content));
    }

    public List<Map<String, String>> getChatHistory() {
        return new ArrayList<>(chatHistory); // Return a copy to avoid direct modifications
    }

    public void clearChatHistory() {
        chatHistory.clear();
    }
}
