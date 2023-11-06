package edu.ntnu.stud;

/**
 * This is the UI class for the train dispatch application.
 */
public class TrainDispatchUi {
  private TrainDeparture[] trainDepartures;

  /**
   * Methode to start the UI.
   */
  public void start() {
    System.out.println(printDepartureTable());

    trainDepartures[0].setTrack(5);
    trainDepartures[0].setDelay(1, 30);
    TrainDeparture.setCurrentTime(9, 35);
    System.out.println(printDepartureTable());

    TrainDeparture.setCurrentTime(17, 50);
    System.out.println(printDepartureTable());

    TrainDeparture.setCurrentTime(18, 40);
    System.out.println(printDepartureTable());
  }

  /**
   * Methode to initialize the UI.
   */
  public void init() {
    trainDepartures = new TrainDeparture[4];
    trainDepartures[0] = new TrainDeparture(501, 8, 15, "1", "Oslo");
    trainDepartures[1] = new TrainDeparture(502, 13, 45, "1", "Trondheim");
    trainDepartures[2] = new TrainDeparture(31, 10, 20, "14", "Mosjøen");
    trainDepartures[3] = new TrainDeparture(135, 18, 39, "12", "Bodø");
  }

  /**
   * Methode that prints a single train departure.
   *
   * @param trainDeparture the train departure to print
   * @return a string representation of the train departure
   */
  private String printTrainDeparture(TrainDeparture trainDeparture) {
    if (trainDeparture.getNewDepartureTime().isBefore(TrainDeparture.getCurrentTime())) {
      return null;
    }
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
    departureTable.append("Current time: ").append(TrainDeparture.getCurrentTime()).append('\n');

    for (TrainDeparture trainDeparture : trainDepartures) {
      if (printTrainDeparture(trainDeparture) != null) {
        departureTable.append(printTrainDeparture(trainDeparture)).append('\n');
      }
    }
    return departureTable.toString();
  }
}
