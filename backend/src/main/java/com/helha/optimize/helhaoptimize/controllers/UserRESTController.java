package com.helha.optimize.helhaoptimize.controllers;

import com.helha.optimize.helhaoptimize.logging.LogExecutionTime;
import com.helha.optimize.helhaoptimize.models.User;
import com.helha.optimize.helhaoptimize.repositories.UserRepository;
import com.helha.optimize.helhaoptimize.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Scope("prototype")
@RequestMapping("/users")
public class UserRESTController {

    @Autowired
    UserService service;

    @GetMapping
    @LogExecutionTime
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping
    @LogExecutionTime
    public User addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @PutMapping
    @LogExecutionTime
    public User updateUser(@RequestBody User user) throws Exception {
        if(user.getId() <= 0){
            throw new Exception("No id provided");
        }
        return service.updateUser(user);
    }

    @DeleteMapping(path = "/{id}")
    @LogExecutionTime
    public void delete(@PathVariable long id){
        service.removeUser(id);
    }
}
