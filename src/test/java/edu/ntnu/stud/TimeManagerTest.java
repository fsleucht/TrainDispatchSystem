package edu.ntnu.stud;

import edu.ntnu.stud.model.TimeManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class tests the TimeManager class. It has both positive and negative tests.
 * The method names explain what is being tested.
 * <p>The test where created with help form GitHub Copilot.
 */
public class TimeManagerTest {

  @Test
  public void setCurrentTimeWithValidInput() {
    int hours = 12;
    int minutes = 30;
    TimeManager.setCurrentTime(hours, minutes);
    assertEquals(hours, TimeManager.getCurrentTime().getHour());
    assertEquals(minutes, TimeManager.getCurrentTime().getMinute());
    TimeManager.resetTime();
  }

  @Test
  public void setCurrentTimeWithInvalidHours() {
    int hours = 24;
    int minutes = 30;
    assertThrows(IllegalArgumentException.class, () -> TimeManager.setCurrentTime(hours, minutes));
  }

  @Test
  public void setCurrentTimeWithInvalidMinutes() {
    int hours = 12;
    int minutes = 60;
    assertThrows(IllegalArgumentException.class, () -> TimeManager.setCurrentTime(hours, minutes));
  }

  @Test
  public void setCurrentTimeWithTimeBeforeCurrentTime() {
    int hours = 12;
    int minutes = 30;
    TimeManager.setCurrentTime(hours, minutes);
    assertThrows(IllegalArgumentException.class, () -> TimeManager.setCurrentTime(hours - 1, minutes));
    TimeManager.resetTime();
  }

  @Test
  public void setCurrentTimeWithTimeAlreadySet() {
    int hours = 12;
    int minutes = 30;
    TimeManager.setCurrentTime(hours, minutes);
    assertThrows(IllegalArgumentException.class, () -> TimeManager.setCurrentTime(hours, minutes));
    TimeManager.resetTime();
  }
}
