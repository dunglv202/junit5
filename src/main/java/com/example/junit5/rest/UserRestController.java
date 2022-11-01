package com.example.junit5.rest;

import com.example.junit5.model.BaseResponse;
import com.example.junit5.model.User;
import com.example.junit5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(BaseResponse.builder()
      .code(1)
      .status(HttpStatus.OK.value())
      .message("Success")
      .data(userService.getAllUser()));
  }

  @PostMapping
  public ResponseEntity<?> addNewUser(@RequestBody User user) {
    userService.addUser(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(
      BaseResponse.builder()
        .code(1)
        .status(HttpStatus.CREATED.value())
        .message("Success")
        .data(null)
    );
  }
}
