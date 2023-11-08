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
     * Konstruktør for TrainDepartures
     * @param departureTime
     * @param line
     * @param trainNumber
     * @param destination
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

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getLine() {
        return line;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getDestination() {
        return destination;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    /**
     * addDelay metoden legger til forsinkelse
     * @param delay
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
     * getDetails metoden returnerer detaljene
     * @return
     */

    public String getDetails() {
        /**
         * String.format() metoden returnerer en formatert Stringg
         */
        return String.format("%s %s %s %s %s", departureTime, line, trainNumber, destination, track == -1 ? "" : track);
    }

}
