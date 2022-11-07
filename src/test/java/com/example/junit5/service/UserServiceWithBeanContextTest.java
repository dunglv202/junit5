package com.example.junit5.service;

import com.example.junit5.model.User;
import com.example.junit5.repository.UserRepository;
import com.example.junit5.repository.UserRepositoryBean;
import com.example.junit5.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceWithBeanContextTest {
    @Autowired
    private UserService userService;

    @TestConfiguration
    public static class BeanConfig {
        @Bean
        UserService userService() {
            return new UserServiceImpl();
        }

        @Bean
        UserRepository userRepository() {
            return new UserRepositoryBean();
//            return new UserRepository() {
//                @Override
//                public List<User> findAll() {
//                    return List.of(new User("any@gmail.com"));
//                }
//            };
        }
    }

    @Test
    void getAllUser() {
        assertIterableEquals(List.of(new User("abc@gmail.com"), new User("def@gmail.com")),
                            userService.getAllUser());
    }
}