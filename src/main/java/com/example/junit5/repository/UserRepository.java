package com.example.junit5.repository;

import com.example.junit5.model.User;

import java.util.List;

public interface UserRepository {
  List<User> findAll();
}
