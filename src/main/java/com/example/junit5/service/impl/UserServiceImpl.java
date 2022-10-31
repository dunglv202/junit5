package com.example.junit5.service.impl;

import com.example.junit5.exception.NotFoundException;
import com.example.junit5.model.User;
import com.example.junit5.repository.UserRepository;
import com.example.junit5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public String getEmailAddressFor(User user) {
    if (user == null) throw new IllegalArgumentException("User must not be null");
    // this could be process of retrieving mail address from database
    // but this is for simplification
    return Optional.of(user.getEmail()).orElseThrow(NotFoundException::new);
  }

}
