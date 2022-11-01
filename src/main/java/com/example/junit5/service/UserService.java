package com.example.junit5.service;

import com.example.junit5.model.User;

import java.util.List;

public interface UserService {
  List<User> getAllUser();

  String getEmailAddressFor(User user);

  void addUser(User user);
}
