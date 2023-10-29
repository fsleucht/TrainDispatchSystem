package edu.ntnu.stud;

public class TrainDispatchUI {
  private TrainDeparture[] trainDepartures;
  public void start() {
    for (TrainDeparture trainDeparture : trainDepartures) {
      System.out.println(trainDeparture.toString());
    }
    trainDepartures[0].setTrack(5);
    trainDepartures[0].setDelay(1, 12);

    System.out.println(" ");
    for (TrainDeparture trainDeparture : trainDepartures) {
      System.out.println(trainDeparture.toString());
    }
  }

  public void init() {
    trainDepartures = new TrainDeparture[4];
    trainDepartures[0] = new TrainDeparture(501, 8, 15, "1", "Oslo");
    trainDepartures[1] = new TrainDeparture(502, 13, 45, "1", "Trondheim");
    trainDepartures[2] = new TrainDeparture(31, 10, 20, "14", "Mosjøen");
    trainDepartures[3] = new TrainDeparture(135, 18, 39, "12", "Bodø");
  }
}
