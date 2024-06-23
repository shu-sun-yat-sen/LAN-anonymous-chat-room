package com.example.demo1.Alservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistantmyService {
    @Autowired
    Assistant assistant;

    public String assistant(String message) {
        return assistant.chat(message);
    }
}
