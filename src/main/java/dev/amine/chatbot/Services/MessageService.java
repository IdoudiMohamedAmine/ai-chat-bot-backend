package dev.amine.chatbot.Services;

import dev.amine.chatbot.Repositorys.MessageRepository;
import dev.amine.chatbot.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    public List<Message> getMessagesByChatId(String chatId) {
        return messageRepository.findByChatId(chatId);
    }
    public void createMessage(Message message){
        messageRepository.save(message);
    }

}
