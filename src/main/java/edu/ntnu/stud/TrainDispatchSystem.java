package edu.ntnu.stud;

import edu.ntnu.stud.Trains.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class TrainDispatchSystem {
    private ArrayList<TrainDeparture> trainDepartures = new ArrayList<>();
    private String currentTime = "00:00";
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            handleUserChoice(choice);
        }
    }

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

    private void displayTrainDepartures() {
        // Sort train departures by departure time
        Collections.sort(trainDepartures, (t1, t2) -> t1.getDepartureTime().compareTo(t2.getDepartureTime()));

        System.out.println("Train Departures:");
        for (TrainDeparture departure : trainDepartures) {
            if (departure.getDepartureTime().compareTo(currentTime) >= 0) {
                System.out.println(departure);
            }
        }
    }

    private void addNewTrainDeparture() {
        System.out.print("Enter departure time (hh:mm): ");
        String departureTime = scanner.nextLine();
        System.out.print("Enter line: ");
        String line = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        String[] timeParts = departureTime.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        TrainDeparture newDeparture = new TrainDeparture(departureTime, line, trainNumber, destination);
        if (hours >= 24 || minutes >= 60) {
            System.out.println("Invalid statement. Time cannot exceed 23:59, and hours above 23 or minutes above 59 is not accepted");}
        else if (!trainDepartures.contains(newDeparture)) {
            trainDepartures.add(newDeparture);
            System.out.println("Train departure added successfully.");
        } else {
            System.out.println("Train with the same number already exists. Cannot add a duplicate.");
        }
    }

    private void assignTrackToTrain() {
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        TrainDeparture departure = findTrainByNumber(trainNumber);

        if (departure != null) {
            System.out.print("Enter track number: ");
            int track = scanner.nextInt();
            scanner.nextLine();
            departure.setTrack(track);
            System.out.println("Track assigned to the train successfully.");
        } else {
            System.out.println("Train not found.");
        }
    }

    private void addDelayToTrain() {
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        TrainDeparture departure = findTrainByNumber(trainNumber);

        if (departure != null) {
            System.out.print("Enter delay (hh:mm): ");
            String delay = scanner.nextLine();
            departure.addDelay(delay);
            System.out.println("Delay added to the train. New departure time: " + departure.getDepartureTime());
        } else {
            System.out.println("Train not found.");
        }
    }

    private void searchByTrainNumber() {
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        TrainDeparture departure = findTrainByNumber(trainNumber);

        if (departure != null) {
            System.out.println("Train Found: " + departure);
        } else {
            System.out.println("Train not found.");
        }
    }

    private TrainDeparture findTrainByNumber(String trainNumber) {
        for (TrainDeparture departure : trainDepartures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                return departure;
            }
        }
        return null;
    }

    private void searchByDestination() {
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.println("Train Departures to " + destination + ":");
        for (TrainDeparture departure : trainDepartures) {
            if (departure.getDestination().equalsIgnoreCase(destination)) {
                System.out.println(departure);
            }
        }
    }

    private void updateCurrentTime() {
        System.out.print("Enter new current time (hh:mm): ");
        String newTime = scanner.nextLine();

        if (newTime.compareTo(currentTime) >= 0) {
            currentTime = newTime;
            System.out.println("Current time updated to " + currentTime);
        } else {
            System.out.println("New time cannot be earlier than the current time.");
        }
    }
}