package com.example.junit5.service.impl;

import com.example.junit5.constant.Colors;
import com.example.junit5.exception.MailingException;
import com.example.junit5.model.User;
import com.example.junit5.service.EmailService;
import com.example.junit5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
  private UserService userService;

  @Autowired
  public EmailServiceImpl(UserService userService) {
    this.userService = userService;
  }

  public void sendEmail(User user, String message) {
    // check for validation
    if (message == null || message.equals("")) {
      throw new MailingException("Message must not be empty");
    }

    // check if user registered an email address
    String emailAddress = userService.getEmailAddressFor(user);

    // send email via smtp server
    System.out.println(Colors.ANSI_BLUE + "Sending mail to " + emailAddress + " with message " + message);
  }
}
