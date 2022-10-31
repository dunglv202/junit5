package com.example.junit5.service;

import com.example.junit5.model.User;
import com.example.junit5.repository.UserRepository;
import com.example.junit5.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  // This must be a concrete class to be injectable
  private UserServiceImpl userService;

//  @BeforeEach
//  void init() {
//    // This works with the same effect as @InjectMocks
//    userService = new UserService(userRepository);
//  }

  @Test
  void getAllUser() {
    List<User> users = List.of(new User("any@gmail.com"), new User("guest@gmail.com"));
    Mockito.when(userRepository.findAll()).thenReturn(users);

    assertIterableEquals(users, userService.getAllUser());

    Mockito.verify(userRepository).findAll();
  }

}