package trains;

import utility.Clock;
import static org.junit.jupiter.api.Assertions.*;

class TrainDeparturesTest {

  /**
   * Test if the departure time is not null.
   * expected result: true, because the departure time is not null.
   * it is "12:00" given by the train object.
   */
  @org.junit.jupiter.api.Test
  void getDepartureTime() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    String departureTime = train.getDepartureTime();

    // Assert
    assertNotNull(departureTime);
  }

  /**
   * Test if the train number is not null.
   * expected result: true, because the train number is not null.
   * it is "123" given by the train object.
   */
  @org.junit.jupiter.api.Test
  void getTrainNumber() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    int trainNumber = train.getTrainNumber();

    // Assert
    assertEquals(123, trainNumber);
  }

  /**
   * Test if the destination is not null.
   * expected result: true, because the destination is not null.
   * it is "Destination City" given by the train object.
   */
  @org.junit.jupiter.api.Test
  void getDestination() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    String destination = train.getDestination();

    // Assert
    assertEquals("Destination City", destination);
  }

  /**
   * Test if the track is not null.
   * expected result: true, because the track is not null.
   * it is "-1" given by the train object.
   * and is by default -1.
   */
  @org.junit.jupiter.api.Test
  void setTrack() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    train.setTrack(-1);

    // Assert
    assertEquals("", train.getTrack());
  }

  /**
   * Test if the delay is calculated correctly.
   * because the delay is calculated correctly by adding each component like this 12:00 + 10:00 -> 12 + 10 and 00 + 00.
   * expected result: "22:00",
   */
  @org.junit.jupiter.api.Test
  void addDelay() {
    // Arrange
    TrainDepartures train = new TrainDepartures("12:00", "L1", 123, "Destination City");

    // Act
    train.addDelay(new Clock(), "10:00", "12:00");

    // Assert
    assertEquals("22:00", train.getDepartureTime());
  }

  /**
   * Test if the details are not null.
   * expected result: true, because the details are not null.
   * it is "12:00 L1 123 Destination City " given by the train object.
   */
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