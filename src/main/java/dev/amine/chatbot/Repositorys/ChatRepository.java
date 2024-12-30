package dev.amine.chatbot.Repositorys;

import dev.amine.chatbot.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String>{
    Chat findChatById(String id);
    List<Chat> findChatsByUserId(String userId);
    List<Chat> findByUserId(String userId);

    List<Chat> findAllByUserId(String userId);
}
