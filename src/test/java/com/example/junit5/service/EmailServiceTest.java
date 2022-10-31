package com.example.junit5.service;

import com.example.junit5.constant.Colors;
import com.example.junit5.exception.MailingException;
import com.example.junit5.model.User;
import com.example.junit5.service.impl.EmailServiceImpl;
import com.example.junit5.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tags(value = @Tag("mail"))
@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
  // @EnabledIf..., @Tag

  EmailServiceTest() {
    System.out.println(Colors.ANSI_GREEN + "Creating new instance of EmailServiceTest");
  }

  @Spy
  private UserService userService = new UserServiceImpl();

  @InjectMocks
  private EmailServiceImpl emailService;

  @BeforeAll
  void init() {
  }

  @BeforeEach
  void initTest() {
    // using lenient() to "bypass" strict stubbing
    Mockito.lenient().doReturn("fixed@gmail.com").when(userService).getEmailAddressFor(Mockito.any());
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
          emailService.sendEmail(new User("anyuser@gmail.com"), "Hmm...");
        });
      }

      // second condition
//      () -> {
//        assertThatThrownBy(() -> {
//          emailService.sendEmail(null , "good!");
//        }).isInstanceOf(IllegalArgumentException.class);
//
//        Mockito.verify(userService).getEmailAddressFor(Mockito.any(User.class));
//      }
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