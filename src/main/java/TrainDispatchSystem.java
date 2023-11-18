import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import trains.TrainDepartures;
import utility.Checker;
import utility.Clock;

/**
 * <p>
 * TrainDispatchSystem class, used for the main system and administration of the train departures.
 * </p>
 *
 * @author Jonas Ingebo, BIDATA 1
 * @version 1.1.0
 * @since 1.0.0
 */

class TrainDispatchSystem {

  /**
   * currentTime is a variable that stores the current time.
   * input is a variable that stores the user input.
   * trainDepartures is a variable that stores the train departures.
   * trainExists is a variable that stores a boolean value for if the train exists.
   */
  private ArrayList<TrainDepartures> trainDepartures;
  private Scanner input;
  private Checker checker;
  private Clock clock;
  private String displayTime;

  /**
   * Initializes a new Train Dispatch System.
   * <br>
   * This constructor creates a new instance of the Train Dispatch System.
   * It sets the initial current time to "00:00," initializes a scanner for input,
   * and creates an empty list to store train departures.
   * <br>
   * Usage example:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   */
  public TrainDispatchSystem() {
    input = new Scanner(System.in);
    trainDepartures = new ArrayList<>();
    checker = new Checker();
    clock = new Clock();
    displayTime = "00:00";
  }

  /**
   * Runs the Train Dispatch System main loop.
   * <br>
   * This method is responsible for running the main loop of the Train Dispatch System.
   * It continuously displays a menu to the user, reads their choice, and handles the
   * user's input until the program is terminated.
   * <br>
   * The main loop is an essential part of the Train Dispatch System's functionality
   * and provides the user with a menu-driven interface for interaction.
   * <br>
   * Note: The loop runs indefinitely, so you should have a termination condition
   * to exit the program gracefully.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.run();
   */
  public void run() {

    while (true) {
      displayMenu();
      int choice = checker.choiceChecker();
      handleUserChoice(choice);
    }

  }

  /**
   * Displays the main menu of the Train Dispatch System.
   * <br>
   * This method displays the main menu for the Train Dispatch System, including
   * various options that allow the user to interact with the system. It shows the
   * current time, lists available menu choices, and prompts the user to enter their
   * choice.
   * <br>
   * The main menu serves as the primary interface for the user to navigate and
   * utilize the system's features.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.displayMenu();
   */
  private void displayMenu() {
    System.out.println("##############################");
    System.out.println("Current time is: " + displayTime);
    System.out.println("Train Dispatch System Menu:");
    System.out.println("1. Display Train Departures");
    System.out.println("2. Add a New Train Departure");
    System.out.println("3. Assign a Track to a Train");
    System.out.println("4. Add Delay to a Train");
    System.out.println("5. Search by Train Number");
    System.out.println("6. Search by Destination");
    System.out.println("7. Update Current Time");
    System.out.println("8. Exit");
    System.out.println("Enter your choice: ");
    System.out.println("##############################");
  }

  /**
   * Handles the user's menu choice in the Train Dispatch System.
   * <br>
   * This method takes the user's menu choice as input and directs the program flow
   * based on the selected option. The available options correspond to various
   * functions in the Train Dispatch System, such as displaying train departures,
   * adding new train departures, assigning tracks, adding delays, and more.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * int userChoice = 1; // Replace with the user's actual choice.
   * system.handleUserChoice(userChoice);
   * <p></p>
   *
   * @param choice The user's menu choice.
   */
  private void handleUserChoice(int choice) {

    switch (choice) {

      case 1 -> displayTrainDepartures();
      case 2 -> addNewTrainDeparture();
      case 3 -> assignTrackToTrain();
      case 4 -> addDelayToTrain();
      case 5 -> searchByTrainNumber();
      case 6 -> searchByDestination();
      case 7 -> displayTime = clock.updateCurrentTime(clock.getCurrentTime());
      case 8 -> {
        System.out.println("Exiting the application.");
        System.out.println("Thank you for using the Train Dispatch System!");
        System.exit(0);
      }

      default -> System.err.println("Invalid choice. Please try again.");

    }

  }

  /**
   * Displays a list of train departures.
   * <br>
   * This method sorts the train departures by departure time and displays a list of
   * upcoming departures. It filters out departures that have already occurred by
   * comparing their departure time with the current time.
   * <br>
   * The displayed train departures include details such as departure time, line,
   * train number, and destination.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.displayTrainDepartures();
   */
  private void displayTrainDepartures() {

    if (trainDepartures.isEmpty()) {
      System.err.println("No train departures found in the system.");
      input.nextLine();
    } else {
      // Sort train departures by departure time
      trainDepartures.sort(Comparator.comparing(TrainDepartures::getDepartureTime));

      System.out.println("Train Departures:");
      trainDepartures.stream()
          .filter(d -> d.getDepartureTime().compareTo(clock.getCurrentTime()) > 0)
          .map(TrainDepartures::getDetails)
          .forEach(System.out::println);
      input.nextLine();
    }

  }

  /**
   * Adds a new train departure to the system.
   * <br>
   * This method guides the user through the process of adding a new train departure to
   * the system. It collects information such as departure time, line, train number, and
   * destination from the user. The method validates the input for time format, boundary,
   * and uniqueness of the train number before adding the new departure.
   * <br>
   * If the time format is invalid or the train number is a duplicate, the method provides
   * error message and exits. Otherwise, it adds the new train departure to the system through the checkNewTrainDeparture method.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.addNewTrainDeparture();
   */
  private void addNewTrainDeparture() {

    System.out.print("Enter departure time (hh:mm): ");
    String departureTime = checker.checkInputTime();

    System.out.print("Enter line: ");
    String line = input.nextLine();

    System.out.println("Enter train number: ");
    int trainNumber = checker.intInputChecker();

    System.out.print("Enter destination: ");
    String destination = input.nextLine();

    String[] timeParts = departureTime.split(":");
    int hours = Integer.parseInt(timeParts[0]);
    int minutes = Integer.parseInt(timeParts[1]);

    // User confirmation
    askUser(trainNumber, hours, minutes, departureTime, line, destination);
  }

  /**
   * Assigns a track to a specific train.
   * <br>
   * This method allows the user to assign a track to a train by entering the train's
   * number and the desired track number. If the specified train is found in the system,
   * the track is assigned to the train, and a success message is displayed. If the train
   * is not found, an error message is shown.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.assignTrackToTrain();
   */
  private void assignTrackToTrain() {

    System.out.print("Enter train number: ");
    int trainNumber = checker.intInputChecker();
    TrainDepartures departure = findTrainByNumber(trainNumber);

    if (departure != null) {
      System.out.print("Enter track number: ");
      int track = checker.intInputChecker();
      departure.setTrack(track);
      System.out.println("Track assigned to the train successfully.");
      input.nextLine();
    } else {
      System.err.println("Train not found.");
      input.nextLine();
    }

  }

  /**
   * Adds a delay to a specific train's departure time.
   * <br>
   * This method allows the user to add a delay to a train's scheduled departure time by
   * entering the train's number and the delay time in 'hh:mm' format. If the specified
   * train is found in the system, the delay is applied, and the new departure time is
   * displayed. If the train is not found, an error message is shown.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.addDelayToTrain();
   */
  private void addDelayToTrain() {

    System.out.print("Enter train number: ");
    int trainNumber = checker.intInputChecker();
    TrainDepartures departure = findTrainByNumber(trainNumber);

    if (departure != null) {
      System.out.print("Enter delay (hh:mm): ");
      String delay = checker.checkInputTime();
      departure.addDelay(clock, delay, departure.getDepartureTime());
      System.out.println("Delay added to the train. New departure time: " + departure.getDepartureTime());
      input.nextLine();
    } else {
      System.err.println("Train not found.");
      input.nextLine();

    }

  }

  /**
   * Searches for a train based on its train number.
   * <br>
   * This method allows the user to search for a train by entering the train's number.
   * If a train with the specified number is found in the system, its details are displayed.
   * If the train is not found, an error message is shown.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.searchByTrainNumber();
   */
  private void searchByTrainNumber() {

    System.out.print("Enter train number: ");
    int trainNumber = checker.intInputChecker();
    TrainDepartures departure = findTrainByNumber(trainNumber);

    if (departure != null) {
      System.out.println("Train Found: " + departure.getDetails());
      input.nextLine();
    } else {
      System.err.println("Train not found.");
      input.nextLine();

    }

  }

  /**
   * Finds a train based on its train number.
   * <br>
   * This method searches for a train with the specified train number in the system's list
   * of train departures. If a train with the matching number is found, it is returned as
   * a result. If no matching train is found, the method returns null.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * int trainNumber = 123; // Replace with the train number to search for.
   * TrainDepartures foundTrain = system.findTrainByNumber(trainNumber);
   *
   * @param trainNumber The train number to search for.
   *
   * @return The TrainDepartures object with the specified train number, or null if not found.
   */
  private TrainDepartures findTrainByNumber(int trainNumber) {

    for (TrainDepartures departure : trainDepartures) {
      if (departure.getTrainNumber() == trainNumber) {
        return departure;
      }

    }

    return null;

  }

  /**
   * Searches for trains based on their destination.
   * <br>
   * This method allows the user to search for trains that have a specific destination by
   * entering the destination name. It then displays a list of train departures that match
   * the provided destination. The comparison is case-insensitive.
   * <br>
   * Example usage:
   * TrainDispatchSystem system = new TrainDispatchSystem();
   * system.searchByDestination();
   */
  private void searchByDestination() {

    System.out.print("Enter destination: ");
    String destination = input.nextLine();
    System.out.println("Train Departures to " + destination + ":");

    for (TrainDepartures departure : trainDepartures) {
      if (departure.getDestination().equalsIgnoreCase(destination)) {
        System.out.println(departure.getDetails());
      }

    }

    input.nextLine();

  }

  private void isTrainUnique(int trainNumber, int hours, int minutes, String departureTime, String line, String destination) {

    TrainDepartures departure = findTrainByNumber(trainNumber);

    if (departure != null) {
      System.err.println("Train with the same number already exists. Cannot add a duplicate.");
      input.nextLine();
    } else if (hours >= 24 || minutes >= 60) {
      System.err.println("Time cannot exceed 23:59, and hours above 23 or minutes above 59 are not accepted");
      input.nextLine();
    } else {
      TrainDepartures newDeparture = new TrainDepartures(departureTime, line, trainNumber, destination);
      trainDepartures.add(newDeparture);
      System.out.println("Train departure added successfully.");
      input.nextLine();
    }

  }

  private void askUser(int trainNumber, int hours, int minutes, String departureTime, String line, String destination) {
    System.out.println("Are you sure you want to add this train departure? (y/n)");

    if (input.nextLine().equalsIgnoreCase("y")) {
      isTrainUnique(trainNumber, hours, minutes, departureTime, line, destination);
    } else {
      System.out.println("Train departure not added.");
      input.nextLine();
    }

  }

}
