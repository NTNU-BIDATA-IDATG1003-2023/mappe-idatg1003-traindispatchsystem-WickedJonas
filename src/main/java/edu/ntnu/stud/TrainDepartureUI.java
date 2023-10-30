package edu.ntnu.stud;

/**
 * Train departure app, used to manage and control train departures over the course of a day.
 * @version 1.0.1
 * @author Jonas Ingebo, BIDATA 1
 * @since 5 Okt, 2023
 */
public class TrainDepartureUI {
    public static void main(String[] args) {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        trainDispatchSystem.run();
    }
}
