package com.mood.mentor.Services;

import com.mood.mentor.Entities.Conversation;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ConversationService {
    // In-memory storage for testing
    private final List<Conversation> chatHistory = new ArrayList<>();

    public List<Conversation> getChatHistory(Long user1Id, Long user2Id) {
        return chatHistory.stream()
                .filter(c -> (c.getUserId().equals(user1Id) && c.getUserId2().equals(user2Id)) ||
                        (c.getUserId().equals(user2Id) && c.getUserId2().equals(user1Id)))
                .toList();
    }

    public void saveMessage(Conversation conversation) {
        chatHistory.add(conversation);
    }

    public List<Map<String, String>> toOpenAiFormat(List<Conversation> history) {
        List<Map<String, String>> formattedHistory = new ArrayList<>();

        // Add system message
        formattedHistory.add(Map.of(
                "role", "system",
                "content", "You are a mental health assistant. Provide supportive and empathetic responses."
        ));

        // Add conversation history
        for (Conversation msg : history) {
            formattedHistory.add(Map.of(
                    "role", msg.getUserId().equals(msg.getUserId2()) ? "assistant" : "user",
                    "content", msg.getMessage()
            ));
        }
        return formattedHistory;
    }
}