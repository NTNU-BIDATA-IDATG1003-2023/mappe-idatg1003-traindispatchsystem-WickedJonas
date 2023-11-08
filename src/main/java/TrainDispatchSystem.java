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
     * Constructor for TrainDispatchSystem
     */

    public TrainDispatchSystem()
    {
        currentTime = "00:00";
        input = new Scanner(System.in);
        trainDepartures = new ArrayList<>();
    }

    /**
     * run() er en metode som kjører programmet
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
     * displayMenu() er en metode som viser menyen
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
     * handleUserChoice() er en metode som håndterer brukerens valg
     * @param choice
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
     * displayTrainDepartures() er en metode som viser alle avgangene
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
     * addNewTrainDeparture() er en metode som legger til en ny avgang
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
     * assignTrackToTrain() er en metode som tildeler et spor til et tog
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
     * addDelayToTrain() er en metode som legger til forsinkelse til et tog
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
     * searchByTrainNumber() er en metode som søker etter et tog basert på tognummer
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
     * findTrainByNumber() er en metode som finner et tog basert på tognummer
     * @param trainNumber
     * @return
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
     * searchByDestination() er en metode som søker etter et tog basert på destinasjon
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
     * updateCurrentTime() er en metode som oppdaterer tiden
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

    private boolean isValidTimeFormat(String time) {
        // Use a regular expression to validate the input format (hh:mm)
        return time.matches("\\d{2}:\\d{2}");
    }
}
