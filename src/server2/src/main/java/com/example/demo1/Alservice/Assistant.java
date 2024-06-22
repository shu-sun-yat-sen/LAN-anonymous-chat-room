package com.example.demo1.Alservice;

import dev.langchain4j.service.Moderate;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
interface Assistant {

    @Moderate
    @SystemMessage("You are a polite assistant in an anonymous chat room")
    String chat(@MemoryId int memoryId,  String userMessage);
}
