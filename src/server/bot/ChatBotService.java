package com.hzsp.demo.service.chatBotService;

import com.hzsp.demo.mapper.DbMapper.RoomRepository;
import com.hzsp.demo.model.Room;
import com.hzsp.demo.model.ChatBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ChatBotService {
    @Autowired
    private RoomRepository roomRepository;

    private Map<String, ChatBot> chatBots = new HashMap<>();

    public void handleBotMessage(String roomId, String message) {
        // Retrieve the room from the database
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            // Get the chat bot for this room
            ChatBot chatBot = chatBots.get(roomId);
            if (chatBot == null) {
                // If there is no chat bot for this room, create one
                chatBot = new ChatBot(roomId);
                chatBots.put(roomId, chatBot);
            }

            chatBot.processMessage(message);
        }
    }
}