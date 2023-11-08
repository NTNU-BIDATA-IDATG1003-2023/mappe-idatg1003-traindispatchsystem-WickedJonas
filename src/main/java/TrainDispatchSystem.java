import trains.*;

import java.util.*;

/**
 * Train departure app, used to manage and control train departures over the course of a day.
 * @version 1.0.0
 * @author Jonas Ingebo, BIDATA 1
 * @since 6 Nov, 2023
 */

class TrainDispatchSystem {

    /**
     * String currentTime er en variabel som lagrer tiden
     * Scanner input er en variabel som lagrer input fra brukeren
     * ArrayList<TrainDepartures> trainDepartures er en variabel som lagrer alle avgangene
     */

    private ArrayList<TrainDepartures> trainDepartures;
    private String currentTime;
    private Scanner input;

    /**
     * Initializes a new Train Dispatch System.
     *
     * This constructor creates a new instance of the Train Dispatch System.
     * It sets the initial current time to "00:00," initializes a scanner for input,
     * and creates an empty list to store train departures.
     *
     * Usage example:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     */

    public TrainDispatchSystem()
    {
        currentTime = "00:00";
        input = new Scanner(System.in);
        trainDepartures = new ArrayList<>();
    }

    /**
     * Runs the Train Dispatch System main loop.
     *
     * This method is responsible for running the main loop of the Train Dispatch System.
     * It continuously displays a menu to the user, reads their choice, and handles the
     * user's input until the program is terminated.
     *
     * The main loop is an essential part of the Train Dispatch System's functionality
     * and provides the user with a menu-driven interface for interaction.
     *
     * Note: The loop runs indefinitely, so you should have a termination condition
     * to exit the program gracefully.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.run();
     */

    public void run() {
        while (true) {
            displayMenu();
            int choice = input.nextInt();
            input.nextLine();
            handleUserChoice(choice);
        }
    }

    /**
     * Displays the main menu of the Train Dispatch System.
     *
     * This method displays the main menu for the Train Dispatch System, including
     * various options that allow the user to interact with the system. It shows the
     * current time, lists available menu choices, and prompts the user to enter their
     * choice.
     *
     * The main menu serves as the primary interface for the user to navigate and
     * utilize the system's features.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.displayMenu();
     */

    private void displayMenu() {
        System.out.println("##############################");
        System.out.println("Current time is: " + currentTime);
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
     *
     * This method takes the user's menu choice as input and directs the program flow
     * based on the selected option. The available options correspond to various
     * functions in the Train Dispatch System, such as displaying train departures,
     * adding new train departures, assigning tracks, adding delays, and more.
     *
     * @param choice The user's menu choice.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * int userChoice = 1; // Replace with the user's actual choice.
     * system.handleUserChoice(userChoice);
     */

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> displayTrainDepartures();
            case 2 -> addNewTrainDeparture();
            case 3 -> assignTrackToTrain();
            case 4 -> addDelayToTrain();
            case 5 -> searchByTrainNumber();
            case 6 -> searchByDestination();
            case 7 -> updateCurrentTime();
            case 8 -> {
                System.out.println("Exiting the application.");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Displays a list of train departures.
     *
     * This method sorts the train departures by departure time and displays a list of
     * upcoming departures. It filters out departures that have already occurred by
     * comparing their departure time with the current time.
     *
     * The displayed train departures include details such as departure time, line,
     * train number, and destination.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.displayTrainDepartures();
     */

    private void displayTrainDepartures() {
        // Sort train departures by departure time
        trainDepartures.sort(Comparator.comparing(TrainDepartures::getDepartureTime));

        System.out.println("Train Departures:");
        trainDepartures.stream()
                .filter(d -> d.getDepartureTime().compareTo(currentTime) >= 0)
                .map(TrainDepartures::getDetails)
                .forEach(System.out::println);
    }

    /**
     * Adds a new train departure to the system.
     *
     * This method guides the user through the process of adding a new train departure to
     * the system. It collects information such as departure time, line, train number, and
     * destination from the user. The method validates the input for time format, boundary,
     * and uniqueness of the train number before adding the new departure.
     *
     * If the time format is invalid or the train number is a duplicate, the method provides
     * error messages and exits. Otherwise, it adds the new train departure to the system.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.addNewTrainDeparture();
     */

    private void addNewTrainDeparture() {
        boolean validTimeFormat = false;
        String departureTime = "";

        while (!validTimeFormat) {
            System.out.print("Enter departure time (hh:mm): ");
            departureTime = input.nextLine();
            if (isValidTimeFormat(departureTime)) {
                validTimeFormat = true;
            } else {
                System.out.println("Invalid departure time format. Use 'hh:mm' format.");
            }
        }

        System.out.print("Enter line: ");
        String line = input.nextLine();

        System.out.print("Enter train number: ");
        int trainNumber = input.nextInt();
        input.nextLine();

        System.out.print("Enter destination: ");
        String destination = input.nextLine();

        // Validate time boundaries
        String[] timeParts = departureTime.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        if (hours >= 24 || minutes >= 60) {
            System.out.println("Invalid time. Time cannot exceed 23:59, and hours above 23 or minutes above 59 are not accepted");
            return; // Exit the method if the time is invalid.
        }

        boolean trainExists = false;

        // Check if a train with the same number already exists
        for (TrainDepartures departure : trainDepartures) {
            if (departure.getTrainNumber() == trainNumber) {
                trainExists = true;
                break;
            }
        }

        if (trainExists) {
            System.out.println("Train with the same number already exists. Cannot add a duplicate.");
        } else {
            TrainDepartures newDeparture = new TrainDepartures(departureTime, line, trainNumber, destination);
            trainDepartures.add(newDeparture);
            System.out.println("Train departure added successfully.");
            input.nextLine();
        }
    }

    /**
     * Assigns a track to a specific train.
     *
     * This method allows the user to assign a track to a train by entering the train's
     * number and the desired track number. If the specified train is found in the system,
     * the track is assigned to the train, and a success message is displayed. If the train
     * is not found, an error message is shown.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.assignTrackToTrain();
     */

    private void assignTrackToTrain() {
        System.out.print("Enter train number: ");
        int trainNumber = input.nextInt();

        TrainDepartures departure = findTrainByNumber(trainNumber);

        if (departure != null)
        {
            System.out.print("Enter track number: ");
            int track = input.nextInt();
            input.nextLine();
            departure.setTrack(track);
            System.out.println("Track assigned to the train successfully.");
            input.nextLine();

        }
        else{
            System.out.println("Train not found.");
        }
    }

    /**
     * Adds a delay to a specific train's departure time.
     *
     * This method allows the user to add a delay to a train's scheduled departure time by
     * entering the train's number and the delay time in 'hh:mm' format. If the specified
     * train is found in the system, the delay is applied, and the new departure time is
     * displayed. If the train is not found, an error message is shown.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.addDelayToTrain();
     */

    private void addDelayToTrain() {
        System.out.print("Enter train number: ");
        int trainNumber = input.nextInt();

        TrainDepartures departure = findTrainByNumber(trainNumber);

        if (departure != null) {
            System.out.print("Enter delay (hh:mm): ");
            String delay = input.nextLine();
            departure.addDelay(delay);
            System.out.println("Delay added to the train. New departure time: " + departure.getDepartureTime());
        } else {
            System.out.println("Train not found.");
        }
    }

    /**
     * Searches for a train based on its train number.
     *
     * This method allows the user to search for a train by entering the train's number.
     * If a train with the specified number is found in the system, its details are displayed.
     * If the train is not found, an error message is shown.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.searchByTrainNumber();
     */

    private void searchByTrainNumber() {
        System.out.print("Enter train number: ");
        int trainNumber = input.nextInt();

        TrainDepartures departure = findTrainByNumber(trainNumber);

        if (departure != null) {
            System.out.println("Train Found: " + departure.getDetails());
        } else {
            System.out.println("Train not found.");
        }
    }

    /**
     * Finds a train based on its train number.
     *
     * This method searches for a train with the specified train number in the system's list
     * of train departures. If a train with the matching number is found, it is returned as
     * a result. If no matching train is found, the method returns null.
     *
     * @param trainNumber The train number to search for.
     * @return The TrainDepartures object with the specified train number, or null if not found.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * int trainNumber = 123; // Replace with the train number to search for.
     * TrainDepartures foundTrain = system.findTrainByNumber(trainNumber);
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
     *
     * This method allows the user to search for trains that have a specific destination by
     * entering the destination name. It then displays a list of train departures that match
     * the provided destination. The comparison is case-insensitive.
     *
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
    }

    /**
     * Updates the current time of the Train Dispatch System.
     *
     * This method allows the user to update the current time by entering a new time
     * in 'hh:mm' format. It compares the new time with the current time, and if the new
     * time is not earlier, it updates the current time. If the new time is earlier than
     * the current time, an error message is displayed.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * system.updateCurrentTime();
     */

    private void updateCurrentTime() {
        System.out.print("Enter new current time (hh:mm): ");
        String newTime = input.nextLine();

        if (newTime.compareTo(currentTime) >= 0) {
            currentTime = newTime;
            System.out.println("Current time updated to " + currentTime);
        } else {
            System.out.println("New time cannot be earlier than the current time.");
        }
    }

    /**
     * Checks if a string represents a valid time format (hh:mm).
     *
     * This method uses a regular expression to validate whether a given string follows
     * the 'hh:mm' time format, where 'hh' represents hours (00-23) and 'mm' represents
     * minutes (00-59).
     *
     * @param time The string to validate as a time format.
     * @return true if the string is a valid time format, false otherwise.
     *
     * Example usage:
     * TrainDispatchSystem system = new TrainDispatchSystem();
     * String time = "12:30"; // Replace with the time to validate.
     * boolean isValid = system.isValidTimeFormat(time);
     */
    private boolean isValidTimeFormat(String time) {
        // Use a regular expression to validate the input format (hh:mm)
        return time.matches("\\d{2}:\\d{2}");
    }
}
