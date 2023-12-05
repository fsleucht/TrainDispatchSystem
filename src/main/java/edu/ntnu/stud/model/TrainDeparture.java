package edu.ntnu.stud.model;

import java.time.LocalTime;

/**
 * Instances of this class represent train departures.
 *
 * <p>It stores information about
 * the train number, departure time, line, destination, track and delay.
 * Track and delay can be set after the object is created.
 */
public class TrainDeparture {
  private final int trainNumber;
  private final LocalTime departureTime;
  private final String line;
  private final String destination;
  private int track;
  private LocalTime delay;
  private LocalTime newDepartureTime;

  /**
   * Constructs a TrainDeparture object. All parameters except track and delay are required when
   * creating a new TrainDeparture object. Track and delay are set to -1 and 0 respectively by
   * and default and can be changed later with the setTrack and setDelay methods.
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
  public TrainDeparture(int trainNumber, int hours, int minutes, String line, String destination)
      throws IllegalArgumentException {
    if (trainNumber <= 0) {
      throw new IllegalArgumentException("Train number must be a positive number");
    }

    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Hours must be between 0 and 23, "
          + "minutes must be between 0 and 59");
    }

    if (LocalTime.of(hours, minutes).isBefore(TimeManager.getCurrentTime())) {
      throw new IllegalArgumentException("Time cannot be set before the current time");
    }

    if (line == null || line.isEmpty()) {
      throw new IllegalArgumentException("Line cannot be null or empty");
    }

    if (destination == null || destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be null or empty");
    }

    this.trainNumber = trainNumber;
    this.departureTime = LocalTime.of(hours, minutes);
    this.line = line;
    this.destination = destination;
    this.newDepartureTime = this.departureTime;
    this.track = -1;
    this.delay = LocalTime.of(0, 0);
  }

  /**
   * Gets the train number.
   *
   * @return train number
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Gets the departure time of the train.
   *
   * @return departure time
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Gets the line the train operates on.
   *
   * @return line
   */
  public String getLine() {
    return line;
  }

  /**
   * Gets the destination of the train.
   *
   * @return destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Gets the track number.
   *
   * @return track
   */
  public int getTrack() {
    return track;
  }

  /**
   * Gets the delay.
   *
   * @return delay
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Gets the new departure time.
   *
   * @return new departure time
   */
  public LocalTime getNewDepartureTime() {
    return newDepartureTime;
  }

  /**
   * Methode that sets the track of the train.
   *
   * @param track the track number of the train
   * @throws IllegalArgumentException if track is not -1 or a positive number
   */
  public void setTrack(int track) throws IllegalArgumentException {
    if (track <= -1 || track == 0) {
      throw new IllegalArgumentException("Track must be -1 or a positive number");
    }
    this.track = track;
  }

  /**
   * Methode that sets the delay of the train. Also sets the new departure time.
   *
   * @param hours number of hours of the delay to be set
   * @param minutes number of minutes of the delay to be set
   * @throws IllegalArgumentException if hours or minutes are not between 0 and 23/59
   */
  public void setDelay(int hours, int minutes) throws IllegalArgumentException {
    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Hours must be between 0 and 23, "
          + "minutes must be between 0 and 59");
    }
    this.delay = LocalTime.of(hours, minutes);
    this.newDepartureTime = this.departureTime.plusHours(hours).plusMinutes(minutes);
  }
}