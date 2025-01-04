package dev.amine.chatbot.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.amine.chatbot.Services.AiService;
import dev.amine.chatbot.Services.ChatService;
import dev.amine.chatbot.Services.MessageService;
import dev.amine.chatbot.model.Chat;
import dev.amine.chatbot.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;
    private final AiService aiService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/new/{userId}")
    public Chat newChat(@PathVariable String userId, @RequestBody Chat chat){
        chat.setUserId(userId);
        return chatService.createChat(chat);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{userId}")
    public List<Chat> getUserChats(@PathVariable String userId){
        return chatService.getChatsByUserId(userId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/{chatId}/message")
    public Message sendMessage(@PathVariable String chatId, @RequestBody Map<String, String> payload) {
        String prompt = payload.get("prompt");
        if (prompt == null || prompt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prompt is missing or empty");
        }
        try {
            // Create and save the user message
            Message userMessage = new Message();
            userMessage.setContent(prompt);
            userMessage.setChatId(chatId);
            userMessage.setSender("user");
            userMessage.setTimestamp(System.currentTimeMillis());
            messageService.createMessage(userMessage);

            // Generate content using AiService
            String aiResponse;
            try {
                aiResponse = aiService.generateContent(prompt);
                // Parse the JSON response to extract the text content
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(aiResponse);
                aiResponse = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
            } catch (Exception e) {
                // Log the exception for debugging
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate AI content", e);
            }

            // Create and save the bot message
            Message botMessage = new Message();
            botMessage.setContent(aiResponse);
            botMessage.setChatId(chatId);
            botMessage.setSender("bot");
            botMessage.setTimestamp(System.currentTimeMillis());
            messageService.createMessage(botMessage);

            return botMessage;
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{chatId}/message")
    public List<Message> getChatMessages(@PathVariable String chatId) {
        List<Message> messages = messageService.getMessagesByChatId(chatId);
        messages.sort(Comparator.comparingLong(Message::getTimestamp));
        return messages;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{chatId}")
    public void deleteChat(@PathVariable String chatId){
        chatService.deleteChat(chatId);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{chatId}")
    public Chat updateChat(@PathVariable String chatId, @RequestBody String title){
        return chatService.updateChat(chatId, title);
    }
}
