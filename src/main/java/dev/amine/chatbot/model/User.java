package dev.amine.chatbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
@AllArgsConstructor
@Getter
@Setter
public class User {
    @MongoId
    private String id;
    private String username;
    private String password;
    private String email;
}
