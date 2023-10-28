package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Instances of this class represent train departures.
 */
public class TrainDeparture {
  private static LocalTime currentTime = LocalTime.of(0, 0);
  private final int trainNumber;
  private final LocalTime departureTime;
  private final String line;
  private final String destination;
  private int track;
  private LocalTime delay = LocalTime.of(0, 0);

  /**
   * Constructs a TrainDeparture object.
   *
   * @param trainNumber the number of the train
   * @param departureTime the departure time of the train
   * @param line the line the train operates on
   * @param destination the destination of the train
   */
  public TrainDeparture(int trainNumber, LocalTime departureTime, String line, String destination) {
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
    this.line = line;
    this.destination = destination;
  }

  public static LocalTime getCurrentTime() {
    return currentTime;
  }

  public int getTrainNumber() {
    return trainNumber;
  }

  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public String getLine() {
    return line;
  }

  public String getDestination() {
    return destination;
  }

  public int getTrack() {
    return track;
  }

  public LocalTime getDelay() {
    return delay;
  }

  public static void setCurrentTime(LocalTime currentTime) {
    TrainDeparture.currentTime = currentTime;
  }

  public void setTrack(int track) {
    this.track = track;
  }

  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

}