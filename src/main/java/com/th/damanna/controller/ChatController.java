package com.th.damanna.controller;

import com.th.damanna.model.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat/sendMessage/{topicID}")
    @SendTo("/topic/{topicID}")
    public ChatMessage sendMessage(@DestinationVariable String topicID, @Payload ChatMessage chatMessage){
        // topicID 별로 나눔
        System.out.println("topicID = " + topicID);
        System.out.println("chatMessage = " + chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/chat/addUser/{topicID}")
    @SendTo("/topic/{topicID}")
    public ChatMessage addUser(@DestinationVariable String topicID, @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        // topicID 별로 나눔
        System.out.println("topicID = " + topicID);
        System.out.println("Sender = " + chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("topicID", topicID);

        return chatMessage;
    }
}
