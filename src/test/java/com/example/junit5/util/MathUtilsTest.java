package com.example.junit5.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MathUtilsTest {

  @Mock
  private MathUtils mathUtils;

  @Test
  void add() {
    mathUtils.add(1, 2, 3);

    ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
    Mockito.verify(mathUtils).add(captor.capture());

    assertIterableEquals(captor.getAllValues(), List.of(1, 2, 3));
  }
}