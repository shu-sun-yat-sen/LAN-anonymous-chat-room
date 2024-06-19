package com.hzsp.demo.model;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class ChatBot {
    private String roomId;
    private String openAIAPI;
    private OpenAiChatModel model;

    public ChatBot(String roomId) {
        this.roomId = roomId;
    }

    public void processMessage(String message) {
        switch (message) {
            case "/help":
                // Handle help instruction
                break;
            // Add more cases for other preset instructions
            default:
                String answer = model.generate(message);
                // System.out.println(answer); // Hello! How can I assist you today?
                break;
        }
    }
}
