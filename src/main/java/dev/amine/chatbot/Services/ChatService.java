package dev.amine.chatbot.Services;

import dev.amine.chatbot.Repositorys.ChatRepository;
import dev.amine.chatbot.Repositorys.MessageRepository;
import dev.amine.chatbot.Repositorys.UserRepository;
import dev.amine.chatbot.model.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    public Chat createChat(Chat chat){
        return chatRepository.save(chat);
    }
    public List<Chat> getChatsByUserId(String userId) {
        return chatRepository.findByUserId(userId);
    }
    public Chat updatechatTitle(String chatId, String title){
        Chat chat = chatRepository.findChatById(chatId);
        chat.setTitle(title);
        return chatRepository.save(chat);
    }
    public void deleteChat(String chatId){
        chatRepository.deleteById(chatId);
    }



}
