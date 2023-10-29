package edu.ntnu.stud;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    int minutes = -1;
    String line = "F4";
    String destination = "Bergen";

    assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(trainNumber, hours, minutes, line, destination));
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
  public void testSetCurrentTimeValidInput() {
    int hours = 12;
    int minutes = 20;
    TrainDeparture.setCurrentTime(hours, minutes);
    assertEquals(hours, TrainDeparture.getCurrentTime().getHour());
    assertEquals(minutes, TrainDeparture.getCurrentTime().getMinute());
  }

  @Test
  public void testSetCurrentTimeInvalidHours() {
    int hours = -1;
    int minutes = 20;
    assertThrows(IllegalArgumentException.class, () -> TrainDeparture.setCurrentTime(hours, minutes));
  }

  @Test
  public void testSetCurrentTimeInvalidMinutes() {
    int hours = 12;
    int minutes = 62;
    assertThrows(IllegalArgumentException.class, () -> TrainDeparture.setCurrentTime(hours, minutes));
  }

  @Test
  public void testSetCurrentTimeInvalidTime() {
    int hours = 12;
    int minutes = 20;
    TrainDeparture.setCurrentTime(hours, minutes);
    assertThrows(IllegalArgumentException.class, () -> TrainDeparture.setCurrentTime(hours - 1, minutes));
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
