package trains;

import utility.Clock;

/**
 * <p>
 * TrainDepartures class, used to represent a train departure.
 * </p>
 *
 * @author Jonas Ingebo, BIDATA 1
 * @version 1.1.0
 * @since 1.0.0
 */

public class TrainDepartures {

  /**
   * String departureTime is a variable that stores the departure time.
   * String line is a variable that stores the line name.
   * int trainNumber is a variable that stores the train number.
   * String destination is a variable that stores the destination name.
   * int track is a variable that stores the track number.
   */
  private String departureTime;
  private String line;
  private int trainNumber;
  private String destination;
  private int track;

  /**
   * Constructs a TrainDepartures object with the specified details.
   * Tracks are not assigned by default, and should be assigned using the setTrack() method.
   *
   * @param departureTime The departure time in 'hh:mm' format.
   * @param line          The line name.
   * @param trainNumber   The train number.
   * @param destination   The destination name.
   */
  public TrainDepartures(String departureTime, String line, int trainNumber, String destination) {
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = -1;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public int getTrainNumber() {
    return trainNumber;
  }

  public String getDestination() {
    return destination;
  }

  public void setTrack(int track) {
    this.track = track;
  }

  public void addDelay(Clock clock, String delay, String departureTime) {
    this.departureTime = clock.calcDelay(delay, departureTime);
  }

  /**
   * Returns a formatted string containing the details of the train departure.
   * <br>
   * This method constructs and returns a formatted string that includes the departure time,
   * line, train number, destination, and, if available, the assigned track. If no track is
   * assigned (track == -1), it is represented as an empty string.
   * <br>
   * Example usage:
   * TrainDepartures departure = new TrainDepartures("12:30", "Line A", 123, "Destination X");
   * String details = departure.getDetails();
   * <br>
   *
   * @return A string containing the details of the train departure.
   */
  public String getDetails() {
    return String.format("%s %s %s %s %s", departureTime, line, trainNumber, destination, track == -1 ? "" : track);
  }

  public String getTrack() {
    return track == -1 ? "" : "Track is " + (track);
  }
}
