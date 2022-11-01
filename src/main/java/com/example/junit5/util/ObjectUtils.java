package com.example.junit5.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtils {
  public static String asJsonString(Object o) {
    try {
      return new ObjectMapper().writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Couldn't parse object " + o);
    }
  }
}
