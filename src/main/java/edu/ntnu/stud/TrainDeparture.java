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
  private int track = -1;
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

  /**
   * Methode that sets the current time. Time can not be set before the current time.
   *
   * @param hours number of hours
   * @param minutes number of minutes
   */
  public static void setCurrentTime(int hours, int minutes) {
    LocalTime setTime = LocalTime.of(hours, minutes);
    if (setTime.isBefore(currentTime)) {
      throw new IllegalArgumentException("Time cannot be set before the current time");
    }
    currentTime = setTime;
  }

  public void setTrack(int track) {
    this.track = track;
  }

  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

}