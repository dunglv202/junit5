package com.example.junit5.service;

import com.example.junit5.constant.Colors;
import com.example.junit5.exception.MailingException;
import com.example.junit5.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmailServiceTest {
  // @EnabledIf..., @Tag

  EmailServiceTest() {
    System.out.println(Colors.ANSI_GREEN + "Creating new instance of EmailServiceTest");
  }

  private EmailService emailService;

  @BeforeAll
  public void init() {
    emailService = new EmailService();
  }

  @Test
  @Disabled("Disabled until bug #42 has been resolved")
  void sendEmailWithBody() {
    User recipient = new User("any@gmail.com");

    assertDoesNotThrow(() -> emailService.sendEmail(recipient, "any message"));
  }

  @Test
  @DisplayName("Send Email Without Message")
  void sendEmailWithoutBody() {
    User recipient = new User("any@gmail.com");

    Assumptions.assumeTrue(recipient.getEmail() != null);
    assertThrows(MailingException.class, () -> emailService.sendEmail(recipient, null));
  }

  @Test
  void sendEmailForOrdinaryCases() {
    assertAll(
      // first condition
      () -> {
        assertDoesNotThrow(() -> {
          emailService.sendEmail(new User("anyuser@gmail.com"), "hmm...");
        });
      },

      // second condition
      () -> {
        assertDoesNotThrow(() -> {
          emailService.sendEmail(new User("an-user@gmail.com"), "good!");
        });
      }
    );
  }

  @Test
//  @EnabledIf("isUsingAssertJ")
  @EnabledIf("com.example.junit5.service.EmailServiceTest#isUsingAssertJ")
  void testSendEmailWithAssertJ() {
    User user = new User("any@gmail.com");

    assumeThat(user).isNotNull();
    assertThatThrownBy(() -> emailService.sendEmail(user, null)).isInstanceOf(MailingException.class);
  }

  static boolean isUsingAssertJ() {
    return true;
  }
}