package edu.ntnu.stud.view;

import edu.ntnu.stud.model.TimeManager;
import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainDepartureRegistry;
import java.util.Scanner;

/**
 * This is the UI class for the train dispatch application.
 */
public class TrainDispatchUi {
  private TrainDepartureRegistry registry;
  private Scanner scanner;
  private static final String PRINT_DEPARTURE_TABLE = "1";
  private static final String ADD_TRAIN_DEPARTURE = "2";
  private static final String ASSIGN_TRACK = "3";
  private static final String ASSIGN_DELAY = "4";
  private static final String SEARCH_TRAIN_DEPARTURE = "5";
  private static final String SEARCH_DESTINATION = "6";
  private static final String UPDATE_TIME = "7";
  private static final String EXIT = "8";

  public TrainDispatchUi() {
  }

  /**
   * Methode to start the UI.
   */
  public void start() {
    boolean exit = false;
    while (!exit) {
      printMenu();
      String choice = scanner.nextLine();
      switch (choice) {
        case PRINT_DEPARTURE_TABLE -> printDepartureTable();
        case ADD_TRAIN_DEPARTURE -> addTrainDeparture();
        case ASSIGN_TRACK -> assignTrack();
        case ASSIGN_DELAY -> assignDelay();
        case SEARCH_TRAIN_DEPARTURE -> searchTrainDeparture();
        case SEARCH_DESTINATION -> searchTrainDepartureDestination();
        case UPDATE_TIME -> updateTime();
        case EXIT -> {
          System.out.println("Exiting Train Dispatch System.");
          exit = true;
        }
        default -> System.out.println("Invalid choice.");
      }
    }
  }

  /**
   * Methode to initialize the UI.
   */
  public void init() {
    this.scanner = new Scanner(System.in);

    this.registry = new TrainDepartureRegistry();
    this.registry.addTrainDeparture(501, 8, 15, "1", "Oslo");
    this.registry.addTrainDeparture(502, 13, 45, "1", "Trondheim");
    this.registry.addTrainDeparture(31, 10, 20, "14", "Mosjøen");
    this.registry.addTrainDeparture(135, 18, 39, "12", "Bodø");
    this.registry.addTrainDeparture(506, 22, 42, "1", "Trondheim");
  }

  private void printMenu() {
    System.out.println("---Train Dispatch System v0.1-------------------Current time: "
        + TimeManager.getCurrentTime() + "---");
    System.out.println("1. Show departure table");
    System.out.println("2. Add Train departure");
    System.out.println("3. Assign track to train departure");
    System.out.println("4. Add delay to train departure");
    System.out.println("5. Search for train departure by train number");
    System.out.println("6. Search for train departure by destination");
    System.out.println("7. Update time");
    System.out.println("8. Exit");
    System.out.print("> ");
  }
  /**
   * Methode that prints a single train departure.
   *
   * @param trainDeparture the train departure to print
   * @return a string representation of the train departure
   */
  private String printTrainDeparture(TrainDeparture trainDeparture) {
    StringBuilder trainDepartureString = new StringBuilder();
    trainDepartureString.append("Number: ").append(trainDeparture.getTrainNumber())
        .append("    Departure: ").append(trainDeparture.getDepartureTime())
        .append("    Line: ").append(trainDeparture.getLine()).append('\'')
        .append("    Destination: '").append(trainDeparture.getDestination()).append('\'');

    if (trainDeparture.getTrack() != -1) {
      trainDepartureString.append("    Track: ").append(trainDeparture.getTrack());
    }
    if (trainDeparture.getDelay().getHour() != 0 && trainDeparture.getDelay().getMinute() != 0) {
      trainDepartureString.append("    Delay: ").append(trainDeparture.getDelay());
    }
    return trainDepartureString.toString();
  }

  /**
   * Methode that prints the departure table.
   *
   */
  private void printDepartureTable() {
    StringBuilder departureTable = new StringBuilder();
    departureTable.append("Current time: ").append(TimeManager.getCurrentTime()).append('\n');

    for (TrainDeparture trainDeparture : this.registry.getTrainDepartureSorted()) {
      departureTable.append(printTrainDeparture(trainDeparture)).append('\n');
    }
    System.out.println(departureTable);
  }

  private void addTrainDeparture() {
    try {
      System.out.print("Train number: ");
      int trainNumber = Integer.parseInt(scanner.nextLine());
      System.out.print("Departure time (hh:mm): ");
      String[] time = scanner.nextLine().split(":");
      int hours = Integer.parseInt(time[0]);
      int minutes = Integer.parseInt(time[1]);
      System.out.print("Line: ");
      String line = scanner.nextLine();
      System.out.print("Destination: ");
      String destination = scanner.nextLine();
      this.registry.addTrainDeparture(trainNumber, hours, minutes, line, destination);
      System.out.println("Train departure added.");
    } catch (IllegalArgumentException e) {
      System.out.println("\n" + e.getMessage());
    }
  }

  private void assignTrack() {
    try {
      System.out.print("Train number: ");
      int trainNumber = Integer.parseInt(scanner.nextLine());
      System.out.print("Track: ");
      int track = Integer.parseInt(scanner.nextLine());
      this.registry.assignTrack(trainNumber, track);
      System.out.println("Track assigned.");
    } catch (IllegalArgumentException e) {
      System.out.println("\n" + e.getMessage());
    }
  }

  private void assignDelay() {
    try {
      System.out.print("Train number: ");
      int trainNumber = Integer.parseInt(scanner.nextLine());
      System.out.print("Delay (hh:mm): ");
      String[] time = scanner.nextLine().split(":");
      int hours = Integer.parseInt(time[0]);
      int minutes = Integer.parseInt(time[1]);
      this.registry.assignDelay(trainNumber, hours, minutes);
      System.out.println("Delay added.");
    } catch (IllegalArgumentException e) {
      System.out.println("\n" + e.getMessage());
    }
  }

  private void searchTrainDeparture() {
    System.out.print("Train number: ");
    int trainNumber = Integer.parseInt(scanner.nextLine());
    if (this.registry.searchTrainDeparture(trainNumber).isPresent()) {
      System.out.println(printTrainDeparture(this.registry.searchTrainDeparture(trainNumber).get()));
    } else {
      System.out.println("Train number " + trainNumber + " not found");
    }
  }

  private void searchTrainDepartureDestination() {
    System.out.print("Destination: ");
    String destination = scanner.nextLine();
    for (TrainDeparture trainDeparture : this.registry.searchTrainDepartureDestination(destination)) {
      System.out.println(printTrainDeparture(trainDeparture));
    }
  }

  private void updateTime() {
    try {
      System.out.print("Hours: ");
      int hours = Integer.parseInt(scanner.nextLine());
      System.out.print("Minutes: ");
      int minutes = Integer.parseInt(scanner.nextLine());
      TimeManager.setCurrentTime(hours, minutes);
      System.out.println("Time updated.");
    } catch (IllegalArgumentException e) {
      System.out.println("\n" + e.getMessage());
    }
  }
}
