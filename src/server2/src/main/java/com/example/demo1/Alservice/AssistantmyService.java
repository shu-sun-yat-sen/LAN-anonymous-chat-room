package com.example.demo1.Alservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistantmyService {
    @Autowired
    Assistant assistant;
<<<<<<< HEAD
    
=======

>>>>>>> 9dd573ae20ab1b223266a7b9c175fcdef6ecdcda
    public String assistant(String message) {
        return assistant.chat(message);
    }
}
