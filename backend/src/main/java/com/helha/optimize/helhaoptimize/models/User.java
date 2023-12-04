package com.helha.optimize.helhaoptimize.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String jsonProfile;

    private HashMap<String,Object> jsonProfileObj;

    private ArrayList<Message> messages;

    public User(int id, String username, String jsonProfile) {
        this.id = id;
        this.username = username;
        setJsonProfile(jsonProfile);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJsonProfile() {
        return jsonProfile;
    }

    public void setJsonProfile(String jsonProfile) {
        this.jsonProfile = jsonProfile;
        ObjectMapper jsonMapper = new ObjectMapper();
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<>() {};
        try {
            setJsonProfileObj(jsonMapper.readValue(jsonProfile,typeRef));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public HashMap<String, Object> getJsonProfileObj() {
        return jsonProfileObj;
    }

    public void setJsonProfileObj(HashMap<String, Object> jsonProfileObj) {
        this.jsonProfileObj = jsonProfileObj;
    }
}
