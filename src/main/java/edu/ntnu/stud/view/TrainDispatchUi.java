package edu.ntnu.stud.view;

import edu.ntnu.stud.model.TimeManager;
import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainDepartureRegistry;
import java.util.List;
import java.util.Scanner;

/**
 * This is the UI class for the train dispatch application.
 *
 * <p>It creates a menu for the user to interact with the application. The menu is created to be
 * used in a terminal window. It allows users to choose from several options using the keyboard.
 * The main menu and the pages that can be accessed from the main menu are created to look the
 * same way, with the name of the page and the current time at the top of the page.
 *
 * <p>The user input is fetched using the Scanner class.
 * It also initializes the registry with some default train departures.
 *
 * <p>Created with help from Copilot.
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
   * Method to start the UI.
   *
   * <p>It prints the start page and waits for the user to press enter. Then it prints the main
   * menu and waits for the user to choose an option. The option is chosen by entering a number
   * between 1 and 8. If the user enters an invalid option, the menu is printed again.
   *
   * <p>When the user chooses an option, the corresponding method is called. After the method
   * is finished, the main menu is printed again.
   *
   * <p>The user can exit the application by entering 8. Then the exit page is printed.
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
   * Method to initialize the UI.
   *
   * <p>It creates a new Scanner object and a new TrainDepartureRegistry object.
   * It also adds some default train departures to the registry.
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

  /**
   * Method that prints the main menu. The numbers in square brackets are the options the user can
   * choose from. The current time is also printed at the top of the page.
   */
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
   * Method that returns a formatted string with the train departure information.
   *
   * <p>If the train departure has a delay, the departure time is crossed out and the new
   * departure time is added next to it and the delay is added in the delay column.
   *
   * <p>If the train departure has been assigned a track, the track number is added in the
   * track column.
   *
   * @param trainDeparture the train departure to be formatted
   * @return a formatted string with the train departure information
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

  /**
   * Method that prints a list of train departures as a table. The table has columns for
   * departure time, line, train number, destination, delay and track.
   *
   * <p>Is used when printing the departure table and when searching for train departures.
   * First the column names are printed. Then each train departure is printed on a new line
   * using the formatTrainDeparture method.
   *
   * @param departureList the list of train departures to be printed
   */
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
   * Method that prints the departure table. The departure table is a list of all train departures
   * in the registry sorted by departure time.
   *
   * <p>Lets the user go back to the main menu by pressing enter.
   */
  private void printDepartureTable() {
    System.out.println("\n\n\n\n\n\n\n\n\n\n" + ANSI_UNDERLINE
        + "   Train Departures                               Current time: "
        + TimeManager.getCurrentTime() + "   " + ANSI_RESET);
    printDepartures(this.registry.getTrainDepartureSorted());
    System.out.print("\nPress [Enter] to go back");
    scanner.nextLine();
  }

  /**
   * Method that lets the user add a train departure to the registry. The user is asked to enter
   * the parameters for the train departure. If the user enters invalid parameters, the method
   * prints an error message.
   *
   * <p>When the train departure is added or has failed to be added, the user is asked if they
   * want to try again or go back to the main menu.
   */
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

  /**
   * Method that lets the user assign a track to a train departure. The user is asked to enter
   * the train number and the track number. If the user enters invalid parameters, the method
   * prints an error message.
   *
   * <p>When the track is assigned or has failed to be assigned, the user is asked if they
   * want to try again or go back to the main menu.
   */
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

  /**
   * Method that lets the user set a delay to a train departure. The user is asked to enter
   * the train number and the delay. If the user enters invalid parameters, the method
   * prints an error message.
   *
   * <p>When the delay is set or has failed to be set, the user is asked if they
   * want to try again or go back to the main menu.
   */
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
        this.registry.setDelay(trainNumber, hours, minutes);
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

  /**
   * Method that lets the user search for a train departure by train number. The user is asked to
   * enter the train number. If the user enters invalid parameters or the train number does not
   * exist in the registry, the method prints an error message.
   *
   * <p>When the train departure is found or has failed to be found, the user is asked if they
   * want to try again or go back to the main menu.
   */
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

  /**
   * Method that lets the user search for train departures by destination. The user is asked to
   * enter the destination. If the user enters invalid parameters or the destination does not
   * exist in the registry, the method prints an error message.
   *
   * <p>When the train departures are found or has failed to be found, the user is asked if they
   * want to try again or go back to the main menu.
   */
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

  /**
   * Method that lets the user update the current time. The user is asked to enter the hours and
   * minutes of the new time. If the user enters invalid parameters or the time is set before or to
   * the current time, the method prints an error message.
   *
   * <p>When the time is updated or has failed to be updated, the user is asked if they
   * want to try again or go back to the main menu.
   */
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

  /**
   * Method that prints the start page.
   */
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

  /**
   * Method that prints the exit page.
   */
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