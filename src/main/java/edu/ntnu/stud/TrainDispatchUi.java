package edu.ntnu.stud;
import java.util.Scanner;

/**
 * This is the UI class for the train dispatch application.
 */
public class TrainDispatchUi {
  private TrainDepartureRegistry registry;
  private Scanner scanner;

  public TrainDispatchUi() {
  }

  /**
   * Methode to start the UI.
   */
  public void start() {
    boolean exit = false;
    while(!exit) {
      System.out.println("---Train Dispatch v0.1-------------------Current time: "
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
      String choice = scanner.nextLine();
      switch (choice) {
        case "1":
          printDepartureTable();
          break;
        case "2":
          addTrainDeparture();
          break;
        case "3":
          assignTrack();
          break;
        case "4":
          assignDelay();
          break;
        case "5":
          searchTrainDeparture();
          break;
        case "6":
          searchTrainDepartureDestination();
          break;
        case "7":
          updateTime();
          break;
        case "8":
          System.out.println("Exiting program.");
          exit = true;
        default:
          System.out.println("Invalid choice.");
      }
    }

    System.out.println("Searching for train number 501");
    if (this.registry.searchTrainDeparture(501).isPresent()) {
      TrainDeparture departure = this.registry.searchTrainDeparture(501).get();
      System.out.println(printTrainDeparture(departure) + "\n");
      departure.setTrack(2);
      departure.setDelay(0, 10);
      System.out.println(printTrainDeparture(departure) + "\n");

    } else {
      System.out.println("Train number 501 not found");
    }

    System.out.println("Searching for trains to Trondheim");
    for (TrainDeparture departure : this.registry.searchTrainDepartureDestination("Trondheim")) {
      System.out.println(printTrainDeparture(departure));
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
