package com.example.demo1.Alservice;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
interface Assistant {

<<<<<<< HEAD
    //todo: to test
    //@Moderate
    //MemoryId
    @SystemMessage("You are a polite assistant in an anonymous chat room")
    String chat(  String userMessage);
=======
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
>>>>>>> 9dd573ae20ab1b223266a7b9c175fcdef6ecdcda
}
