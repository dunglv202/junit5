package com.example.junit5.util;

import java.util.Arrays;

public class MathUtils {
  int add(int... a) {
    return Arrays.stream(a).reduce(0, Integer::sum);
  }
}
