package com.lxl.demo.mapper;

import com.lxl.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends JpaRepository<User,String> {
    public User findByUsernameAndPassword(String username,String Password);
    public User findByUsername(String username);

}