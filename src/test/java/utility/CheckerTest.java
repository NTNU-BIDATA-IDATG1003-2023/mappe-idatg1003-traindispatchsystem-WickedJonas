package utility;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

  @org.junit.jupiter.api.Test
  void isValidTimeFormat() {
    // Arrange
    Checker checker = new Checker();

    // Act
    boolean isValid = checker.isValidTimeFormat("12:00");

    // Assert
    assertTrue(isValid);
  }

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