package edu.ntnu.stud;

import edu.ntnu.stud.model.TimeManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeManagerTest {

  @Test
  public void setCurrentTimeWithValidInput() {
    int hours = 12;
    int minutes = 30;
    TimeManager.setCurrentTime(hours, minutes);
    assertEquals(hours, TimeManager.getCurrentTime().getHour());
    assertEquals(minutes, TimeManager.getCurrentTime().getMinute());
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
  }
}
