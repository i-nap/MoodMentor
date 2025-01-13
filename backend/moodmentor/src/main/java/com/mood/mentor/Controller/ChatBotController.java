package com.mood.mentor.Controller;

import com.mood.mentor.Mapper.ChatBotMapper;
import com.mood.mentor.Services.ChatBotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatBotController {
    private final ChatBotService chatBotService;
    private final ChatBotMapper chatBotMapper;

    public ChatBotController(ChatBotService chatBotService, ChatBotMapper chatBotMapper) {
        this.chatBotService = chatBotService;
        this.chatBotMapper = chatBotMapper;
    }

    @GetMapping("/history")
    public ResponseEntity<List<Map<String, String>>> getChatHistory(
            @RequestParam int userId,
            @RequestParam int chatId) {
        List<Map<String, String>> chatHistory = chatBotMapper.getChat(userId, chatId);
        return ResponseEntity.ok(chatHistory);
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendMessage(
            @RequestBody Map<String, Object> request) throws Exception {
        String userText = (String) request.get("userText");
        int userId = (Integer) request.get("userId");
        int chatId = (Integer) request.get("chatId");

        Map<String, Object> response = chatBotService.processChat(userText, userId, chatId);
        return ResponseEntity.ok(response);
    }
}