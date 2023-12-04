package com.helha.optimize.helhaoptimize.services;

import com.helha.optimize.helhaoptimize.models.Message;
import com.helha.optimize.helhaoptimize.models.User;
import com.helha.optimize.helhaoptimize.repositories.MessageRepository;
import com.helha.optimize.helhaoptimize.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository repository;

    public List<Message> getAllMessages(){
        return repository.findAll();
    }


    public Message addMessage(Message message){
        return repository.save(message);
    }

    public Message updateMessage(Message message){
        return repository.save(message);
    }

    public void removeMessage(long id){
        repository.deleteById(id);
    }
}
