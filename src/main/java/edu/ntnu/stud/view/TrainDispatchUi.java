package edu.ntnu.stud.view;

import edu.ntnu.stud.model.TimeManager;
import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainDepartureRegistry;
import java.util.List;
import java.util.Scanner;

/**
 * This is the UI class for the train dispatch application.
 */
public class TrainDispatchUi {
  private TrainDepartureRegistry registry;
  private Scanner scanner;

  //fields for menu choices
  private static final String PRINT_DEPARTURE_TABLE = "1";
  private static final String ADD_TRAIN_DEPARTURE = "2";
  private static final String ASSIGN_TRACK = "3";
  private static final String ASSIGN_DELAY = "4";
  private static final String SEARCH_TRAIN_DEPARTURE = "5";
  private static final String SEARCH_DESTINATION = "6";
  private static final String UPDATE_TIME = "7";
  private static final String EXIT = "8";

  //fields for ANSI escape codes
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_UNDERLINE = "\u001B[4m";
  private static final String ANSI_BOLD = "\u001B[1m";
  private static final String ANSI_CROSSED_OUT = "\u001B[9m";

  /**
   * Methode to start the UI.
   */
  public void start() {
    printStartPage();
    scanner.nextLine();
    boolean exit = false;
    while (!exit) {
      printMenu();
      String choice = scanner.nextLine();
      switch (choice) {
        case PRINT_DEPARTURE_TABLE -> printDepartureTable();
        case ADD_TRAIN_DEPARTURE -> addTrainDeparture();
        case ASSIGN_TRACK -> assignTrack();
        case ASSIGN_DELAY -> setDelay();
        case SEARCH_TRAIN_DEPARTURE -> searchTrainDepartureNumber();
        case SEARCH_DESTINATION -> searchTrainDepartureDestination();
        case UPDATE_TIME -> updateTime();
        case EXIT -> exit = true;
        default -> System.out.println("Invalid choice");
      }
    }
    printExitPage();
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
    System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
        + "   Main menu                                    Current time: "
        + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
    System.out.println("[1] Show departure table");
    System.out.println("[2] Add departure");
    System.out.println("[3] Assign track to departure");
    System.out.println("[4] Set delay to departure");
    System.out.println("[5] Search for departure by train number");
    System.out.println("[6] Search for departure(s) by destination");
    System.out.println("[7] Update time");
    System.out.println("[8] Exit");
    System.out.print("> ");
  }

  /**
   * Methode that prints a single train departure.
   *
   * @param trainDeparture the train departure to print
   * @return a string representation of the train departure
   */
  private String formatTrainDeparture(TrainDeparture trainDeparture) {
    String format = "%-6s %-9s | %-6s | %-8s | %-13s | %-7s | %-5s";
    String trainNumber = String.valueOf(trainDeparture.getTrainNumber());
    String departureTime = String.valueOf(trainDeparture.getDepartureTime());
    String newDepartureTime = "";
    String line = trainDeparture.getLine();
    String destination = trainDeparture.getDestination();
    String track = "";
    String delay = "";
    if (trainDeparture.getTrack() != -1) {
      track = String.valueOf(trainDeparture.getTrack());
    }
    if (trainDeparture.getDelay().getHour() != 0 || trainDeparture.getDelay().getMinute() != 0) {
      delay = "+" + trainDeparture.getDelay();
      newDepartureTime = String.valueOf(trainDeparture.getNewDepartureTime());
      departureTime = ANSI_CROSSED_OUT + departureTime + ANSI_RESET + " ";
    }
    return String.format(format, departureTime, newDepartureTime, line, trainNumber, destination,
        delay, track);
  }

  private void printDepartures(List<TrainDeparture> departureList) {
    System.out.println(ANSI_BOLD + "Departure time" + ANSI_RESET + "   | "
        + ANSI_BOLD + "Line" + ANSI_RESET + "   | "
        + ANSI_BOLD + "Number" + ANSI_RESET + "   | "
        + ANSI_BOLD + "Destination" + ANSI_RESET + "   | "
        + ANSI_BOLD + "Delay" + ANSI_RESET + "   | "
        + ANSI_BOLD + "Track" + ANSI_RESET);
    for (TrainDeparture trainDeparture : departureList) {
      System.out.println(formatTrainDeparture(trainDeparture));
    }
  }

  /**
   * Methode that prints the departure table.
   *
   */
  private void printDepartureTable() {
    System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
        + "   Train Departures                               Current time: "
        + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
    printDepartures(this.registry.getTrainDepartureSorted());
    System.out.print("\nPress [Enter] to go back");
    scanner.nextLine();
  }

  private void addTrainDeparture() {
    boolean exit = false;
    while (!exit) {
      System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
          + "   Add a departure                          Current time: "
          + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
      try {
        System.out.print("Train number: ");
        final int trainNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Departure hours: ");
        final int hours = Integer.parseInt(scanner.nextLine());
        System.out.print("Departure minutes: ");
        final int minutes = Integer.parseInt(scanner.nextLine());
        System.out.print("Line: ");
        final String line = scanner.nextLine();
        System.out.print("Destination: ");
        final String destination = scanner.nextLine();
        this.registry.addTrainDeparture(trainNumber, hours, minutes, line, destination);
        System.out.println(ANSI_BOLD + "\nTrain departure added." + ANSI_RESET);
      } catch (IllegalArgumentException e) {
        System.out.println(ANSI_BOLD + "\nFailed for the following reason:\n"
            + ANSI_RESET + e.getMessage());
      }
      System.out.println("\nPress [Enter] to go back or [1] to try again");
      System.out.print("> ");
      String choice = scanner.nextLine();
      if (!choice.equals("1")) {
        exit = true;
      }
    }
  }

  private void assignTrack() {
    boolean exit = false;
    while (!exit) {
      System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
          + "   Assign track to departure                      Current time: "
          + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
      try {
        System.out.print("Train number: ");
        int trainNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Track: ");
        int track = Integer.parseInt(scanner.nextLine());
        this.registry.assignTrack(trainNumber, track);
        System.out.println(ANSI_BOLD + "\nTrack assigned." + ANSI_RESET);
      } catch (IllegalArgumentException e) {
        System.out.println(ANSI_BOLD + "\nFailed for the following reason:\n"
            + ANSI_RESET + e.getMessage());
      }
      System.out.println("\nPress [Enter] to go back or [1] to try again");
      System.out.print("> ");
      String choice = scanner.nextLine();
      if (!choice.equals("1")) {
        exit = true;
      }
    }
  }

  private void setDelay() {
    boolean exit = false;
    while (!exit) {
      System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
          + "   Set departure delay                            Current time: "
          + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
      try {
        System.out.print("Train number: ");
        int trainNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Hours: ");
        String hoursString = scanner.nextLine();
        int hours = hoursString.isEmpty() ? 0 : Integer.parseInt(hoursString);
        System.out.print("Minutes: ");
        int minutes = Integer.parseInt(scanner.nextLine());
        this.registry.assignDelay(trainNumber, hours, minutes);
        System.out.println(ANSI_BOLD + "\nDelay added." + ANSI_RESET);
      } catch (IllegalArgumentException e) {
        System.out.println(ANSI_BOLD + "\nFailed for the following reason:\n"
            + ANSI_RESET + e.getMessage());
      }
      System.out.println("\nPress [Enter] to go back or [1] to try again");
      System.out.print("> ");
      String choice = scanner.nextLine();
      if (!choice.equals("1")) {
        exit = true;
      }
    }
  }

  private void searchTrainDepartureNumber() {
    boolean exit = false;
    while (!exit) {
      System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
          + "   Search for departure by number                 Current time: "
          + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
      try {
        System.out.print("Train number: ");
        int trainNumber = Integer.parseInt(scanner.nextLine());
        printDepartures(this.registry.searchTrainDeparture(trainNumber));
      } catch (IllegalArgumentException e) {
        System.out.println(ANSI_BOLD + "\nFailed for the following reason:\n"
            + ANSI_RESET + e.getMessage());
      }
      System.out.println("\nPress [Enter] to go back or [1] to try again");
      System.out.print("> ");
      String choice = scanner.nextLine();
      if (!choice.equals("1")) {
        exit = true;
      }
    }
  }

  private void searchTrainDepartureDestination() {
    boolean exit = false;
    while (!exit) {
      System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
          + "   Search for departure by destination            Current time: "
          + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
      try {
        System.out.print("Destination: ");
        String destination = scanner.nextLine();
        printDepartures(this.registry.searchTrainDepartureDestination(destination));
      } catch (IllegalArgumentException e) {
        System.out.println(ANSI_BOLD + "\nFailed for the following reason:\n"
            + ANSI_RESET + e.getMessage());
      }
      System.out.println("\nPress [Enter] to go back or [1] to try again");
      System.out.print("> ");
      String choice = scanner.nextLine();
      if (!choice.equals("1")) {
        exit = true;
      }
    }
  }

  private void updateTime() {
    boolean exit = false;
    while (!exit) {
      System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
          + "   Update time                                    Current time: "
          + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
      try {
        System.out.print("Hours: ");
        String hoursString = scanner.nextLine();
        int hours = hoursString.isEmpty() ? 0 : Integer.parseInt(hoursString);
        System.out.print("Minutes: ");
        int minutes = Integer.parseInt(scanner.nextLine());
        TimeManager.setCurrentTime(hours, minutes);
        System.out.println(ANSI_BOLD + "\nTime updated." + ANSI_RESET);
        registry.removePassedTrainDepartures();
      } catch (IllegalArgumentException e) {
        System.out.println(ANSI_BOLD + "\nFailed for the following reason:\n"
            + ANSI_RESET + e.getMessage());
      }
      System.out.println("\nPress [Enter] to go back or [1] to try again");
      System.out.print("> ");
      String choice = scanner.nextLine();
      if (!choice.equals("1")) {
        exit = true;
      }
    }
  }

  private void printStartPage() {
    System.out.print("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
        + "                                                                      "
        + "\n\n\n\n" + ANSI_RESET + ANSI_BOLD
        + "\n                      Train Dispatch System v0.1                      "
        + ANSI_RESET
        + "\n\n\n\n                      Press [Enter] to continue                       "
        + ANSI_UNDERLINE
        + "\n                                                                      "
        + ANSI_RESET);
  }

  private void printExitPage() {
    System.out.print("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
        + "                                                                      "
        + "\n\n\n\n" + ANSI_RESET + ANSI_BOLD
        + "\n                      Exiting System...                      "
        + ANSI_RESET + ANSI_UNDERLINE
        + "\n\n\n\n\n                                                                      "
        + ANSI_RESET);
  }
}