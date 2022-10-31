package com.example.junit5.service.impl;

import com.example.junit5.constant.Colors;
import com.example.junit5.exception.MailingException;
import com.example.junit5.model.User;
import com.example.junit5.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
  public void sendEmail(User user, String message) {
    // check for validation
    if (message == null || message.equals("")) {
      throw new MailingException("Message must not be empty");
    }

    // send email via smtp server
    System.out.println(Colors.ANSI_BLUE + "Sending mail to user with message " + message);
  }
}
