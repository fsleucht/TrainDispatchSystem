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

  public TrainDeparture(int trainNumber, LocalTime departureTime, String line, String destination) {
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
    this.line = line;
    this.destination = destination;
  }


}