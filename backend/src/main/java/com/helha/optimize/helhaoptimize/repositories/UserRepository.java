package com.helha.optimize.helhaoptimize.repositories;

import com.helha.optimize.helhaoptimize.models.User;
import com.springboot.data.jpa.repository.JPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository extends JPARepository<User,Long> {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM \"User\"",(rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("jsonProfile")
                ));
    }

    @Override
    public User save(User user){
        if(user.getId() > 0){
            jdbcTemplate.update("UPDATE \"User\" SET username=?, jsonProfile=? WHERE id=?",user.getUsername(),user.getJsonProfile(),user.getId());
        }else{
            jdbcTemplate.update("INSERT INTO \"User\" (username,jsonProfile) VALUES (?,?)",user.getUsername(),user.getJsonProfile());
            int id = jdbcTemplate.queryForObject("SELECT max(id) from \"User\"",Integer.class);
            user.setId(id);
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM \"User\" WHERE id=?",id);
    }
}
