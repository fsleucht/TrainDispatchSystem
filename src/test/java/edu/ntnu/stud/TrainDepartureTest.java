package edu.ntnu.stud;

import edu.ntnu.stud.model.TimeManager;
import edu.ntnu.stud.model.TrainDeparture;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the TrainDeparture class. It has both positive and negative tests.
 * The method names explain what is being tested.
 * <p>The test where created with help form GitHub Copilot.
 */
public class TrainDepartureTest {

  @Test
  public void testConstructorValidInput() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";

    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, hours, minutes, line, destination);

    assertEquals(trainNumber, trainDeparture.getTrainNumber());
    assertEquals(hours, trainDeparture.getDepartureTime().getHour());
    assertEquals(minutes, trainDeparture.getDepartureTime().getMinute());
    assertEquals(line, trainDeparture.getLine());
    assertEquals(destination, trainDeparture.getDestination());
    assertEquals(-1, trainDeparture.getTrack());
    assertEquals(0, trainDeparture.getDelay().getHour());
    assertEquals(0, trainDeparture.getDelay().getMinute());
    assertEquals(hours, trainDeparture.getNewDepartureTime().getHour());
    assertEquals(minutes, trainDeparture.getNewDepartureTime().getMinute());
  }

  @Test
  public void testConstructorInvalidTrainNumber() {
    int trainNumber = -1;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";

    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(trainNumber, hours, minutes, line, destination));
  }

  @Test
  public void testConstructorInvalidHours() {
    int trainNumber = 30;
    int hours = -1;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";

    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(trainNumber, hours, minutes, line, destination));
  }

  @Test
  public void testConstructorInvalidMinutes() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 60;
    String line = "F4";
    String destination = "Bergen";

    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(trainNumber, hours, minutes, line, destination));
  }

  @Test
  public void testConstructorInvalidTime() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";

    TimeManager.setCurrentTime(hours, minutes);
    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(trainNumber, hours - 1, minutes, line, destination));
  }

  @Test
  public void testConstructorInvalidLine() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "";
    String destination = "Bergen";

    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(trainNumber, hours, minutes, line, destination));
  }

  @Test
  public void testConstructorInvalidDestination() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "";

    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(trainNumber, hours, minutes, line, destination));
  }


  @Test
  public void testSetTrackValidInput() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, hours, minutes, line, destination);
    int track = 5;
    trainDeparture.setTrack(track);
    assertEquals(track, trainDeparture.getTrack());
  }

  @Test
  public void testSetTrackInvalidInput() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, hours, minutes, line, destination);
    int track = -2;
    assertThrows(IllegalArgumentException.class, () -> trainDeparture.setTrack(track));
  }

  @Test
  public void testSetDelayValidInput() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, hours, minutes, line, destination);
    int delayHours = 1;
    int delayMinutes = 30;
    trainDeparture.setDelay(delayHours, delayMinutes);
    assertEquals(delayHours, trainDeparture.getDelay().getHour());
    assertEquals(delayMinutes, trainDeparture.getDelay().getMinute());
  }

  @Test
  public void testSetDelayInvalidHours() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, hours, minutes, line, destination);
    int delayHours = -1;
    int delayMinutes = 30;
    assertThrows(IllegalArgumentException.class, () -> trainDeparture.setDelay(delayHours, delayMinutes));
  }

  @Test
  public void testSetDelayInvalidMinutes() {
    int trainNumber = 30;
    int hours = 12;
    int minutes = 20;
    String line = "F4";
    String destination = "Bergen";
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, hours, minutes, line, destination);
    int delayHours = 1;
    int delayMinutes = -1;
    assertThrows(IllegalArgumentException.class, () -> trainDeparture.setDelay(delayHours, delayMinutes));
  }
}
