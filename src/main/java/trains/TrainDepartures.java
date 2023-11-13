package trains;

/**
 * Train departure app, used to manage and control train departures over the course of a day.
 * @version 1.0.0
 * @author Jonas Ingebo, BIDATA 1
 * @since 6 Nov, 2023
 */
public class TrainDepartures {

    /**
     * String departureTime er en variabel som lagrer avgangstiden
     * String line er en variabel som lagrer linjen
     * int trainNumber er en variabel som lagrer tognummeret
     * String destination er en variabel som lagrer destinasjonen
     * int track er en variabel som lagrer spornummeret
     */

    private String departureTime;
    private String line;
    private int trainNumber;
    private String destination;
    private int track;

    /**
     * Constructs a TrainDepartures object with the specified details.
     *
     * @param departureTime The departure time in 'hh:mm' format.
     * @param line The line name.
     * @param trainNumber The train number.
     * @param destination The destination name.
     *
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

    /**
     * Adds a delay to the train's departure time.
     *
     * This method allows for the addition of a delay to the train's scheduled departure time.
     * It takes a delay in 'hh:mm' format as input and updates the departure time accordingly.
     * The method handles the rollover of minutes and hours, ensuring the time remains within
     * valid boundaries (00:00 to 23:59).
     *
     * @param delay The delay in 'hh:mm' format to be added to the departure time.
     *
     * Example usage:
     * TrainDepartures departure = new TrainDepartures("12:30", "Line A", 123, "Destination X");
     * departure.addDelay("02:15"); // Adds a delay of 2 hours and 15 minutes to the departure time.
     */

    public void addDelay(String delay) {

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
        this.departureTime = String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Returns a formatted string containing the details of the train departure.
     *
     * This method constructs and returns a formatted string that includes the departure time,
     * line, train number, destination, and, if available, the assigned track. If no track is
     * assigned (track == -1), it is represented as an empty string.
     *
     * @return A string containing the details of the train departure.
     *
     * Example usage:
     * TrainDepartures departure = new TrainDepartures("12:30", "Line A", 123, "Destination X");
     * String details = departure.getDetails();
     */

    public String getDetails() {
        return String.format("%s %s %s %s %s", departureTime, line, trainNumber, destination, track == -1 ? "" : track);
    }

}
