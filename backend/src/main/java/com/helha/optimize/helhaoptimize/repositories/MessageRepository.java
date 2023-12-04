package com.helha.optimize.helhaoptimize.repositories;

import com.helha.optimize.helhaoptimize.models.Message;
import com.springboot.data.jpa.repository.JPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MessageRepository extends JPARepository<Message, Long> {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("SELECT * FROM \"Message\"",(rs, rowNum) ->
                new Message(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getInt("user_id")
                ));
    }

    @Override
    public Message save(Message message){
        if(message.getId() > 0){
            jdbcTemplate.update("UPDATE \"Message\" SET message=?, user_id=? WHERE id=?",message.getMessage(),message.getUser_id(),message.getId());
        }else{
            jdbcTemplate.update("INSERT INTO \"Message\" (message,user_id) VALUES (?,?)",message.getMessage(),message.getUser_id());
            int id = jdbcTemplate.queryForObject("SELECT max(id) from \"Message\"",Integer.class);
            message.setId(id);
        }
        return message;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM \"Message\" WHERE id=?",id);
    }
}
