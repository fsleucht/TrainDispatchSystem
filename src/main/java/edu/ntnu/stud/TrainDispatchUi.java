package edu.ntnu.stud;

/**
 * This is the UI class for the train dispatch application.
 */
public class TrainDispatchUi {
  private TrainDepartureRegistry registry;

  public TrainDispatchUi() {
  }

  /**
   * Methode to start the UI.
   */
  public void start() {
    System.out.println(printDepartureTable());

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

    TimeManager.setCurrentTime(9, 35);
    System.out.println(printDepartureTable());

    TimeManager.setCurrentTime(17, 50);
    System.out.println(printDepartureTable());

    TimeManager.setCurrentTime(18, 40);
    System.out.println(printDepartureTable());
  }

  /**
   * Methode to initialize the UI.
   */
  public void init() {
    this.registry = new TrainDepartureRegistry();
    this.registry.addTrainDeparture(501, 8, 15, "1", "Oslo");
    this.registry.addTrainDeparture(502, 13, 45, "1", "Trondheim");
    this.registry.addTrainDeparture(31, 10, 20, "14", "Mosjøen");
    this.registry.addTrainDeparture(135, 18, 39, "12", "Bodø");
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
   * @return a string representation of the departure table
   */
  private String printDepartureTable() {
    StringBuilder departureTable = new StringBuilder();
    departureTable.append("Current time: ").append(TimeManager.getCurrentTime()).append('\n');

    for (TrainDeparture trainDeparture : this.registry.getTrainDepartureSorted()) {
      departureTable.append(printTrainDeparture(trainDeparture)).append('\n');
    }
    return departureTable.toString();
  }
}
