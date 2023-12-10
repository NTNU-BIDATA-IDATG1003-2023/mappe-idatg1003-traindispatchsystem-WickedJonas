package utility;

import java.util.Scanner;

/**
 * <p>
 * Checker class, used to validate user input.
 * </p>
 *
 * @author Jonas Ingebo, BIDATA 1
 * @version 1.1.0
 * @since 1.1.0
 */

public class Checker {
  private Scanner input;

  public Checker() {
    input = new Scanner(System.in);
  }

  /**
   * Checks if a string represents a valid time format (hh:mm).
   * <br>
   * This method uses a regular expression to validate whether a given string follows
   * the 'hh:mm' time format, where 'hh' represents hours (00-23) and 'mm' represents
   * minutes (00-59).
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * String time = "12:30"; // Replace with the time to validate.
   * boolean isValid = system.isValidTimeFormat(time);
   *
   * @param time The string to validate as a time format.
   *
   * @return true if the string is a valid time format, false otherwise.
   */
  public boolean isValidTimeFormat(String time) {
    // Use a regular expression to validate the input format (hh:mm)
    return time.matches("\\d{2}:\\d{2}");
  }

  /**
   * Validates and retrieves a time input from the user in the 'hh:mm' format.
   * This method prompts the user to input a time and continues to ask for input
   * until a valid time format is provided ('hh:mm'). It returns the validated time.
   * <br>
   * Example usage:
   * String userTime = checkInputTime();
   *
   * @return The validated time input provided by the user in the 'hh:mm' format.
   */
  public String checkInputTime() {

    boolean validTimeFormat = false;
    String departureTime = "";

    while (!validTimeFormat) {

      departureTime = input.nextLine();

      if (isValidTimeFormat(departureTime)) {
        validTimeFormat = true;
      } else {
        System.err.println("Only numbers are accepted. Use 'hh:mm' format: ");
      }

    }

    return departureTime;

  }

  /**
   * Validates and retrieves an integer input from the user.
   * This method prompts the user to input an integer and continues to ask for input
   * until a valid integer is provided. It returns the validated integer.
   * <br>
   * Example usage:
   * int userInteger = intInputChecker();
   *
   * @return The validated integer input provided by the user.
   */
  public int intInputChecker() {

    boolean validIntInput = false;
    int intInput = 0;
    while (!validIntInput) {

      try {
        intInput = input.nextInt();
        validIntInput = true;
        input.nextLine();
      } catch (Exception e) {
        System.err.println("Must only contain numbers");
        input.nextLine();
      }

    }
    return intInput;
  }

  /**
   * Validates and retrieves an integer input within the range of 1-9 from the user.
   * This method prompts the user to input an integer and continues to ask for input
   * until a valid integer within the specified range is provided. It returns the validated integer.
   * <br>
   * Example usage:
   * int userChoice = choiceChecker();
   *
   * @return The validated integer input provided by the user.
   */
  public int choiceChecker() {

    boolean validIntInput = false;
    int intInput = 0;
    while (!validIntInput) {

      try {
        intInput = input.nextInt();
        if (intInput > 0 && intInput < 10) {
          validIntInput = true;
          input.nextLine();
        } else {
          System.err.println("Please try again. Must be a number between 1-9");
          input.nextLine();
        }
      } catch (Exception e) {
        System.err.println("Please try again. Must be a number between 1-9");
        input.nextLine();
      }

    }
    return intInput;
  }

  /**
   * Validates and retrieves a non-empty string input from the user.
   * This method prompts the user to input a string and continues to ask for input
   * until a non-empty string is provided. It returns the validated string.
   * <br>
   * Example usage:
   * String userInput = stringInputChecker();
   *
   * @return The non-empty string input provided by the user.
   */
  public String stringInputChecker() {
    boolean validStringInput = false;
    String stringInput = "";
    while (!validStringInput) {
      stringInput = input.nextLine();
      if (!stringInput.isEmpty()) {
        validStringInput = true;
      } else {
        System.err.println("Cannot be empty. Please try again.");
      }
    }

    return stringInput;

  }

}
