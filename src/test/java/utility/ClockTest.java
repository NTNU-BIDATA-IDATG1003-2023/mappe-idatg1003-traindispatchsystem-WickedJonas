package utility;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {

  @org.junit.jupiter.api.Test
  void getCurrentTime() {
    // Arrange
    Clock clock = new Clock();

    // Act
    String currentTime = clock.getCurrentTime();

    // Assert
    assertNotNull(currentTime);
  }

  @org.junit.jupiter.api.Test
  void calcDelay() {
    // Arrange
    Clock clock = new Clock();

    // Act
    String delay = clock.calcDelay("00:30", "12:00");

    // Assert
    assertEquals("12:30", delay);
  }

}