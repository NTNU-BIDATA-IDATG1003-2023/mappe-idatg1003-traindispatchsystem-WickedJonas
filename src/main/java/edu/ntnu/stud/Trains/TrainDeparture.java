package edu.ntnu.stud.Trains;


public class TrainDeparture {
    private String departureTime;
    private String line;
    private int trainNumber;
    private String destination;
    private int track;

    public TrainDeparture(String departureTime, String line, int trainNumber, String destination) {
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

    public void addDelay(String delay) {
        String[] timeParts = departureTime.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        String[] delayParts = delay.split(":");
        int delayHours = Integer.parseInt(delayParts[0]);
        int delayMinutes = Integer.parseInt(delayParts[1]);

        hours += delayHours;
        minutes += delayMinutes;

        // Handle rollover to the next hour
        if (minutes >= 60) {
            minutes = minutes % 60;
            hours++;
        }

        if (hours >= 24) {
            hours = hours % 24;
        }

        this.departureTime = String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", departureTime, line, trainNumber, destination, track == -1 ? "" : track);
    }

}