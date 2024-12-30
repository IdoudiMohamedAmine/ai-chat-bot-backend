package dev.amine.chatbot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatBotApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("GEMINI_API_KEY", dotenv.get("GEMINI_API_KEY"));
        System.setProperty("GEMINI_API_URL", dotenv.get("GEMINI_API_URL"));
        SpringApplication.run(ChatBotApplication.class, args);
    }
}