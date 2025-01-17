package dev.amine.chatbot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;

@Document(collection = "messages")
@RequiredArgsConstructor
@Getter
@Setter
public class Message {
    @MongoId
    private String id;
    private String chatId;
    private String sender;
    private String content;
    private Long timestamp;

}
