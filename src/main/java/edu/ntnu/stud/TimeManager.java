package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This class is responsible for storing and updating the current time.
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
   * Methode that sets the current time.
   *
   * @param hours number of hours
   * @param minutes number of minutes
   * @throws IllegalArgumentException if hours or minutes are not between 0 and 23/59 or
   *                                  if time is set before the current time
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
    currentTime = setTime;
  }
}
