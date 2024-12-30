package dev.amine.chatbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "chats")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chat {
    @MongoId
    private String id;
    private String userId;
    private String title;
}
