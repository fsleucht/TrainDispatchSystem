package edu.ntnu.stud;

import edu.ntnu.stud.view.TrainDispatchUi;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  /**
   * Main method for the application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    TrainDispatchUi ui = new TrainDispatchUi();
    ui.init();
    ui.start();
  }
}
