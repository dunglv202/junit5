package com.example.junit5.service.impl;

import com.example.junit5.model.User;
import com.example.junit5.repository.UserRepository;
import com.example.junit5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUser() {
    return userRepository.findAll();
  }

}
