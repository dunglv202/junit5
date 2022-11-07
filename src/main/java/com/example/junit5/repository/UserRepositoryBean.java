package com.example.junit5.repository;

import com.example.junit5.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryBean implements UserRepository {
    @Override
    public List<User> findAll() {
        return List.of(new User("abc@gmail.com"), new User("def@gmail.com"));
    }
}
