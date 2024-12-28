package dev.amine.chatbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

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
    private Date createdAt;
}