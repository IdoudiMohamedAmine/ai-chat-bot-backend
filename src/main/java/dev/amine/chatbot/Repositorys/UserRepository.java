package dev.amine.chatbot.Repositorys;

import dev.amine.chatbot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
