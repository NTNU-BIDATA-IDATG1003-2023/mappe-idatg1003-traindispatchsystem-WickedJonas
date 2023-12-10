package utility;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {

  /**
   * Test if the current time is not null.
   * expected result: true, because the current time is not null.
   * it is "00:00" by default.
   */
  @org.junit.jupiter.api.Test
  void getCurrentTime() {
    // Arrange
    Clock clock = new Clock();

    // Act
    String currentTime = clock.getCurrentTime();

    // Assert
    assertNotNull(currentTime);
  }

  /**
   * Test if the delay is calculated correctly.
   * expected result: "12:30",
   * because the delay is calculated correctly by adding each component like this 12:00 + 00:30 -> 12 + 00 and 00 + 30.
   * expected result: "12:30",
   */
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