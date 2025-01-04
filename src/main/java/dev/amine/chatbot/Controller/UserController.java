package dev.amine.chatbot.Controller;

import dev.amine.chatbot.Services.UserService;
import dev.amine.chatbot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        return userService.loginUser(user.getEmail(), user.getPassword());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get/{email}")
    public User getUser(@PathVariable String email){
        return userService.getUser(email);
    }

}
