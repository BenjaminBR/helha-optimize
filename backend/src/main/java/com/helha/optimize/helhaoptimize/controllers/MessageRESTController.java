package com.helha.optimize.helhaoptimize.controllers;

import com.helha.optimize.helhaoptimize.logging.LogExecutionTime;
import com.helha.optimize.helhaoptimize.models.Message;
import com.helha.optimize.helhaoptimize.repositories.MessageRepository;
import com.helha.optimize.helhaoptimize.repositories.MessageRepository;
import com.helha.optimize.helhaoptimize.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Scope("prototype")
@RequestMapping("/messages")
public class MessageRESTController {

    @Autowired
    MessageService service;

    @GetMapping
    @LogExecutionTime
    public List<Message> getAllMessages(){
        return service.getAllMessages();
    }

    @PostMapping
    @LogExecutionTime
    public Message addMessage(@RequestBody Message message){
        return service.addMessage(message);
    }

    @PutMapping
    @LogExecutionTime
    public Message updateMessage(@RequestBody Message message) throws Exception {
        if(message.getId() <= 0){
            throw new Exception("No id provided");
        }
        return service.updateMessage(message);
    }

    @DeleteMapping
    @LogExecutionTime
    public void delete(@RequestParam long id){
        service.removeMessage(id);
    }
}
