package utility;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

  /**
   * Test if the input is a valid time format.
   * expected result: true, based on the input "12:00", because it is in "hh:mm" format.
   */
  @org.junit.jupiter.api.Test
  void isValidTimeFormat() {
    // Arrange
    Checker checker = new Checker();

    // Act
    boolean isValid = checker.isValidTimeFormat("12:00");

    // Assert
    assertTrue(isValid);
  }

  /**
   * Test if the input is not a valid time format.
   * expected result: false, based on the input "kk:kk", because it is not in "hh:mm" format.
   */
  @org.junit.jupiter.api.Test
  void isNotValidTimeFormat() {
    // Arrange
    Checker checker = new Checker();

    // Act
    boolean isValid = checker.isValidTimeFormat("kk:kk");

    // Assert
    assertFalse(isValid);
  }
}