package com.example.junit5.service;

import com.example.junit5.model.User;

public interface EmailService {
  void sendEmail(User user, String message);
}
