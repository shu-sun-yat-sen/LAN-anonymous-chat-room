package com.example.demo1.Alservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.langchain4j.service.MemoryId;

@Service
public class AssistantmyService {
    @Autowired
    Assistant assistant;
    
    public String assistant(@MemoryId int memoryId,String message) {
        return assistant.chat(memoryId, message);
    }
}
