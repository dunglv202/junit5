package com.example.junit5.exception;

public class MailingException extends RuntimeException {
  public MailingException(String message) {
    super(message);
  }
}
