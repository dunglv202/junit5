package com.example.junit5.service;

import com.example.junit5.constant.Colors;
import com.example.junit5.exception.MailingException;
import com.example.junit5.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmailServiceTest {
  EmailServiceTest() {
    System.out.println(Colors.ANSI_GREEN + "Creating new instance of EmailServiceTest");
  }

  private EmailService emailService;

  @BeforeAll
  public void init() {
    emailService = new EmailService();
  }

  @Test
  void sendEmailWithBody() {
    User recipient = new User("any@gmail.com");

    assertDoesNotThrow(() -> emailService.sendEmail(recipient, "any message"));
  }

  @Test
  void sendEmailWithoutBody() {
    User recipient = new User("any@gmail.com");

    assertThrows(MailingException.class, () -> emailService.sendEmail(recipient, null));
  }
}