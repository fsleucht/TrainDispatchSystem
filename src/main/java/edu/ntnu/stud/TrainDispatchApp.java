package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  public static void main(String[] args) {
    TrainDispatchUI ui = new TrainDispatchUI();
    ui.init();
    ui.start();
  }
}
