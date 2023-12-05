package edu.ntnu.stud.model;

import java.time.LocalTime;

/**
 * This class is responsible managing the time in the program. It sores the current time
 * as a LocalTime object and has methods for getting and setting the current time.
 * The time is stored as a static variable, so the same time is used throughout the program.
 * The current time is set to 00:00 by default.
 */
public class TimeManager {
  private static LocalTime currentTime = LocalTime.of(0, 0);

  /**
   * Gets the current time.
   *
   * @return current time
   */
  public static LocalTime getCurrentTime() {
    return currentTime;
  }

  /**
   * Methode that sets the current time. Hours and minutes are given as parameters.
   * Time cannot be set before the current time or to the current time.
   * The time must be set using the 24-hour clock.
   *
   * @param hours number of hours
   * @param minutes number of minutes
   * @throws IllegalArgumentException if hours or minutes are not between 0 and 23/59,
   *                                  if time is set before the current time or
   *                                  if time is already set to the given time
   */
  public static void setCurrentTime(int hours, int minutes) throws IllegalArgumentException {
    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Hours must be between 0 and 23, "
          + "minutes must be between 0 and 59");
    }
    LocalTime setTime = LocalTime.of(hours, minutes);
    if (setTime.isBefore(currentTime)) {
      throw new IllegalArgumentException("Time cannot be set before the current time");
    }
    if (setTime.equals(currentTime)) {
      throw new IllegalArgumentException("Time already set to " + currentTime);
    }
    currentTime = setTime;
  }

  /**
   * Methode that resets the current time. Only used for testing.
   */
  public static void resetTime() {
    currentTime = LocalTime.of(0, 0);
  }
}
