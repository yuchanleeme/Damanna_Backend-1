package com.th.damanna.controller;

import com.th.damanna.model.ChatMessage;
import com.th.damanna.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        logger.info("새로운 연결을 받았습니다.");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionConnectedEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String topicID = (String) headerAccessor.getSessionAttributes().get("topicID");

        if(username != null){
            logger.info(username + "유저의 연결이 끊겼습니다.");
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(username);

            messageTemplate.convertAndSend("/topic/"+topicID,chatMessage);
        }
    }
}
