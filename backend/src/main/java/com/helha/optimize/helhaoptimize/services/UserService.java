package com.helha.optimize.helhaoptimize.services;

import com.helha.optimize.helhaoptimize.models.Message;
import com.helha.optimize.helhaoptimize.models.User;
import com.helha.optimize.helhaoptimize.repositories.UserRepository;
import org.ahlhe.http.jsonUtils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    MessageService messageService;

    public List<User> getAllUsers(){
        List<User> users = repository.findAll();
        users.stream().forEach(user -> user.setMessages(
                (ArrayList<Message>) messageService.getAllMessages().stream().filter(message -> message.getUser_id() == user.getId()).collect(Collectors.toList()))
        );
        return users;
    }

    public User addUser(User user){
        if(JSONUtils.isValidJSONString(user.getJsonProfile())){
            return repository.save(user);
        }
        return user;
    }

    public User updateUser(User user){
        if(JSONUtils.isValidJSONString(user.getJsonProfile())){
            return repository.save(user);
        }
        return user;
    }

    public void removeUser(long id){
        repository.deleteById(id);
    }
}
