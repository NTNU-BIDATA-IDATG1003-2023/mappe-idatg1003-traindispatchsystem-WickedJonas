package utility;

import java.util.Scanner;

/**
 * <p>
 * Clock class, used to represent the clock of the Train Dispatch System.
 * </p>
 *
 * @author Jonas Ingebo, BIDATA 1
 * @version 1.1.0
 * @since 1.1.0
 */

public class Clock {
  private Scanner clockInput;
  private Checker checker;
  private String currentTime;


  /**
   * Constructor for the Clock class.
   * Initializes the clock input scanner,
   * a Checker instance,
   * and sets the initial current time to "00:00".
   * <br>
   * Example usage:
   * Clock clock = new Clock();
   */
  public Clock() {

    clockInput = new Scanner(System.in);
    checker = new Checker();
    currentTime = "00:00";

  }

  /**
   * Returns the current time of the Train Dispatch System.
   * <br>
   * This method returns the current time of the Train Dispatch System as a string
   * in 'hh:mm' format.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * String time = system.getCurrentTime();
   *
   * @return The current time of the Train Dispatch System in 'hh:mm' format.
   */
  public String getCurrentTime() {
    return currentTime;
  }

  /**
   * Adds a delay to the train's departure time.
   * <br>
   * This method allows for the addition of a delay to the train's scheduled departure time.
   * It takes a delay in 'hh:mm' format as input and updates the departure time accordingly.
   * The method handles the rollover of minutes and hours, ensuring the time remains within
   * valid boundaries (00:00 to 23:59).
   * <br>
   * Example usage:
   * TrainDepartures departure = new TrainDepartures("12:30", "Line A", 123, "Destination X");
   * departure.addDelay("02:15"); // Adds a delay of 2 hours and 15 minutes to the departure time.
   *
   * @param delay The delay in 'hh:mm' format to be added to the departure time.
   */
  public String calcDelay(String delay, String departureTime) {

    // Split the departure time into hours and minutes
    String[] timeParts = departureTime.split(":");
    int hours = Integer.parseInt(timeParts[0]);
    int minutes = Integer.parseInt(timeParts[1]);

    // Split the delay into hours and minutes
    String[] delayParts = delay.split(":");
    int delayHours = Integer.parseInt(delayParts[0]);
    int delayMinutes = Integer.parseInt(delayParts[1]);

    // Add the delay to the departure time
    hours += delayHours;
    minutes += delayMinutes;

    // Handle rollover to the next hour
    if (minutes >= 60) {
      minutes = minutes % 60;
      hours++;
    }

    // Handle rollover to the next day
    if (hours >= 24) {
      hours = hours % 24;
    }

    // Format the new departure time
    return String.format("%02d:%02d", hours, minutes);
  }

  /**
   * Updates the current time of the Train Dispatch System.
   * <br>
   * This method allows the user to update the current time by entering a new time
   * in 'hh:mm' format. It compares the new time with the current time, and if the new
   * time is not earlier, it updates the current time. If the new time is earlier than
   * the current time, an error message is displayed.
   * <br>
   * compareTo usage:
   * Goes through the time Strings and checks each character.
   * ex. newTime = 12:30 vs currentTime = 12:31.
   * reads first 1, then 2, then :, then 3, then 0, vs 1, then 2, then :, then 3, then 1.
   * 12:30 is earlier than 12:31, so it returns -1.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.updateCurrentTime();
   */
  public String updateCurrentTime(String currentTime) {

    System.out.print("Enter new current time (hh:mm): ");
    boolean validTimeChange = false;
    String newTime = "";
    int givenHours;
    int givenMinutes;
    while (!validTimeChange) {

      String givenTime = checker.checkInputTime();

      String[] timeParts = givenTime.split(":");
      givenHours = Integer.parseInt(timeParts[0]);
      givenMinutes = Integer.parseInt(timeParts[1]);

      if (givenTime.compareTo(currentTime) >= 0 && givenMinutes < 60 && givenHours < 24) {
        newTime = givenTime;
        validTimeChange = true;
        System.out.println("Current time updated to " + newTime);
        clockInput.nextLine();
      } else {
        System.err.println("Must be a later time than the current time, and hours smaller than 24 and minutes smaller than 60");
      }

    }
    return this.currentTime = newTime;
  }
}
