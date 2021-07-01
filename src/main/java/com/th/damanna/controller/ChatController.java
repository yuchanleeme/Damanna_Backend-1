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

    @MessageMapping("/chat/sendMessage/{roomID}")
    @SendTo("/topic/{roomID}")
    public ChatMessage sendMessage(@DestinationVariable String roomID, @Payload ChatMessage chatMessage){
        // roomID 별로 나눔
        System.out.println("topicID = " + roomID);
        System.out.println("chatMessage = " + chatMessage.getContent());
        return chatMessage; // 채팅 메세지 리턴
    }
}
