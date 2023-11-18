/**
 * <p>
 * TrainDispatchApp class, used to run the TrainDispatchSystem.
 * </p>
 *
 * @author Jonas Ingebo, BIDATA 1
 * @version 1.1.0
 * @since 1.0.0
 */

public class TrainDispatchApp {
  /**
   * Main method for the TrainDispatchApp.
   *
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {

    TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
    trainDispatchSystem.run();

  }
}

