package com.example.junit5.rest;

import com.example.junit5.model.User;
import com.example.junit5.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockitoPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.example.junit5.util.ObjectUtils.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserRestController userRestController;

  private MockMvc mockMvc;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
  }

  @Test
  void getAllUsers() throws Exception {
    Mockito.doReturn(List.of(new User("@any"), new User("@next"))).when(userService).getAllUser();

    mockMvc.perform(get("/api/v1/users"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.data", hasSize(2)));

    Mockito.verify(userService).getAllUser();
  }

  @Test
  void testAddUser() throws Exception {
    mockMvc.perform(post("/api/v1/users")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(asJsonString(new User("newuser@gmail.com"))))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.message", is("Success")));

    Mockito.verify(userService).addUser(Mockito.any(User.class));
  }
}