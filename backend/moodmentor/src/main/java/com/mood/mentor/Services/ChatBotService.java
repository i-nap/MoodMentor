package com.mood.mentor.Services;

import com.mood.mentor.Mapper.ChatBotMapper;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ChatBotService {
    private final OpenAiService openAiService;
    private final ChatBotMapper chatBotMapper;

    public ChatBotService(OpenAiService openAiService, ChatBotMapper chatBotMapper) {
        this.openAiService = openAiService;
        this.chatBotMapper = chatBotMapper;
    }

    public Map<String, Object> processChat(String userText, int userId, int chatId) {
        // Save user message
        chatBotMapper.saveUserChat(userText, userId, chatId);

        // Get chat history
        List<Map<String, String>> chatHistory = chatBotMapper.getChat(userId, chatId);

        // Format history for OpenAI
        List<Map<String, String>> formattedHistory = formatChatHistory(chatHistory);

        // Get AI response
        String aiResponse = openAiService.getCustomizedResponse(formattedHistory);
        System.out.println(aiResponse);
        // Save AI response
        chatBotMapper.saveChat(aiResponse, userId, chatId);

        return Map.of(
                "userMessage", userText,
                "chatbotResponse", aiResponse
        );
    }

    private List<Map<String, String>> formatChatHistory(List<Map<String, String>> history) {
        List<Map<String, String>> formatted = new ArrayList<>();

        // Add system message
        formatted.add(Map.of(
                "role", "system",
                "content", "You are a mental health assistant specializing in basic mental health evaluations. Your goal is to have a supportive conversation, ask relevant questions based on previous responses, and assess the user for potential conditions like depression, anxiety, or stress. Provide a simple rating for each condition on a scale of 1-10 and adapt your questions based on the user's responses."
        ));

        // Add conversation history
        for (Map<String, String> msg : history) {
            if (msg.containsKey("user_text")) {
                formatted.add(Map.of("role", "user", "content", msg.get("user_text")));
            }
            if (msg.containsKey("chat_text")) {
                formatted.add(Map.of("role", "assistant", "content", msg.get("chat_text")));
            }
        }

        return formatted;
    }
}