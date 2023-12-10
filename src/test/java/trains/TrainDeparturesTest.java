package trains;

import utility.Clock;
import static org.junit.jupiter.api.Assertions.*;

class TrainDeparturesTest {

  @org.junit.jupiter.api.Test
  void getDepartureTime() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    String departureTime = train.getDepartureTime();

    // Assert
    assertNotNull(departureTime);
  }

  @org.junit.jupiter.api.Test
  void getTrainNumber() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    int trainNumber = train.getTrainNumber();

    // Assert
    assertEquals(123, trainNumber);
  }

  @org.junit.jupiter.api.Test
  void getDestination() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    String destination = train.getDestination();

    // Assert
    assertEquals("Destination City", destination);
  }

  @org.junit.jupiter.api.Test
  void setTrack() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    train.setTrack(-1);

    // Assert
    assertEquals("", train.getTrack());
  }

  @org.junit.jupiter.api.Test
  void addDelay() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    train.addDelay(new Clock(), "10:00", "12:00");

    // Assert
    assertEquals("22:00", train.getDepartureTime());
  }

  @org.junit.jupiter.api.Test
  void getDetails() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    String details = train.getDetails();

    // Assert
    assertNotNull(details);
    assertEquals("12:00 L1 123 Destination City ", details);
  }
}