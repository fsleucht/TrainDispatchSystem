package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Instances of this class represent train departures.
 *
 * <p>It stores information about
 * the train number, departure time, line, destination, track and delay.
 * Track and delay can be set after the object is created.
 * The class is responsible for storing and updating the current time.
 * It also has a toString method that returns a String representation of the object.
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
   * @param hours the hour of departure
   * @param minutes the minute of departure
   * @param line the line the train operates on
   * @param destination the destination of the train
   * @throws IllegalArgumentException if trainNumber is not a positive number,
   *                                  if hours or minutes are not between 0 and 23/59,
   *                                  if time is set before the current time,
   *                                  if line is null or empty or
   *                                  if destination is null or empty
   */
  public TrainDeparture(int trainNumber, int hours, int minutes, String line, String destination) {
    if (trainNumber <= 0) {
      throw new IllegalArgumentException("Train number must be a positive number");
    }

    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Hours must be between 0 and 23, "
          + "minutes must be between 0 and 59");
    }
    LocalTime setTime = LocalTime.of(hours, minutes);
    if (setTime.isBefore(currentTime)) {
      throw new IllegalArgumentException("Departure time cannot be before the current time");
    }

    if (line == null || line.isEmpty()) {
      throw new IllegalArgumentException("Line cannot be null or empty");
    }

    if (destination == null || destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be null or empty");
    }

    this.trainNumber = trainNumber;
    this.departureTime = setTime;
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
   * Methode that sets the current time.
   *
   * @param hours number of hours
   * @param minutes number of minutes
   * @throws IllegalArgumentException if hours or minutes are not between 0 and 23/59 or
   *                                  if time is set before the current time
   */
  public static void setCurrentTime(int hours, int minutes) {
    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Hours must be between 0 and 23, "
          + "minutes must be between 0 and 59");
    }
    LocalTime setTime = LocalTime.of(hours, minutes);
    if (setTime.isBefore(currentTime)) {
      throw new IllegalArgumentException("Time cannot be set before the current time");
    }
    currentTime = setTime;
  }

  /**
   * Methode that sets the track of the train.
   *
   * @param track the track number of the train
   * @throws IllegalArgumentException if track is not -1 or a positive number
   */
  public void setTrack(int track) {
    if (track <= -1 || track == 0) {
      throw new IllegalArgumentException("Track must be -1 or a positive number");
    }
    this.track = track;
  }

  /**
   * Methode that sets the delay of the train.
   *
   * @param hours number of hours
   * @param minutes number of minutes
   * @throws IllegalArgumentException if hours or minutes are not between 0 and 23/59
   */
  public void setDelay(int hours, int minutes) {
    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Hours must be between 0 and 23, "
          + "minutes must be between 0 and 59");
    }
    this.delay = LocalTime.of(hours, minutes);
  }

  /**
   * Simple toString methode that returns a String representation of the TrainDeparture object.
   *
   * @return String representation of the TrainDeparture object.
   */
  public String toString() {
    if (this.departureTime.isBefore(currentTime)) {
      return null;
    }

    if (track == -1) {
      return "TrainDeparture{"
          + "trainNumber=" + trainNumber
          + ", departureTime=" + departureTime
          + ", line='" + line + '\''
          + ", track="
          + ", destination='" + destination + '\''
          + '}';
    }

    return "TrainDeparture{"
        + "trainNumber=" + trainNumber
        + ", departureTime=" + departureTime
        + ", line='" + line + '\''
        + ", destination='" + destination + '\''
        + ", track=" + track
        + ", delay=" + delay
        + '}';
  }
}