package edu.ntnu.stud;

import edu.ntnu.stud.model.TimeManager;
import edu.ntnu.stud.model.TrainDeparture;
import edu.ntnu.stud.model.TrainDepartureRegistry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the TrainDepartureRegistry class. It has both positive and negative tests.
 * The method names explain what is being tested.
 * <p>The test where created with help form GitHub Copilot.
 */
public class TrainDepartureRegistryTest {

  @Test
  public void testAddTrainDeparture() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    TrainDeparture trainDeparture = (new TrainDeparture(30, 12, 20, "F4", "Bergen"));
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    assertEquals(1, registry.getTrainDepartureSorted().size());
    assertEquals(trainDeparture.getTrainNumber(), registry.getTrainDepartureSorted().get(0).getTrainNumber());
    assertEquals(trainDeparture.getDepartureTime(), registry.getTrainDepartureSorted().get(0).getDepartureTime());
    assertEquals(trainDeparture.getLine(), registry.getTrainDepartureSorted().get(0).getLine());
    assertEquals(trainDeparture.getDestination(), registry.getTrainDepartureSorted().get(0).getDestination());
    assertEquals(trainDeparture.getTrack(), registry.getTrainDepartureSorted().get(0).getTrack());
    assertEquals(trainDeparture.getDelay(), registry.getTrainDepartureSorted().get(0).getDelay());
    assertEquals(trainDeparture.getNewDepartureTime(), registry.getTrainDepartureSorted().get(0).getNewDepartureTime());
  }

  @Test
  public void testAddTrainDepartureDuplicateTrainNumber() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    assertThrows(IllegalArgumentException.class, () -> registry.addTrainDeparture(30, 12, 20, "F4", "Bergen"));
  }

  @Test
  public void testSearchTrainDeparture() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    assertEquals(1, registry.searchTrainDeparture(30).size());
    assertEquals(30, registry.searchTrainDeparture(30).get(0).getTrainNumber());
  }


  @Test
  public void testSearchTrainDepartureTrainNumberDoesNotExist() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    assertThrows(IllegalArgumentException.class, () -> registry.searchTrainDeparture(30));
  }

  @Test
  public void testSearchTrainDepartureDestination() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    assertEquals(1, registry.searchTrainDepartureDestination("Bergen").size());
    assertEquals("Bergen", registry.searchTrainDepartureDestination("Bergen").get(0).getDestination());
  }

  @Test
  public void testSearchTrainDepartureDestinationDestinationDoesNotExist() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    assertThrows(IllegalArgumentException.class, () -> registry.searchTrainDepartureDestination("Bergen"));
  }

  @Test
  public void testSearchTrainDepartureDestinationDestinationIsNullOrEmpty() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    assertThrows(IllegalArgumentException.class, () -> registry.searchTrainDepartureDestination(null));
    assertThrows(IllegalArgumentException.class, () -> registry.searchTrainDepartureDestination(""));
  }

  @Test
  public void testRemovePassedTrainDepartures() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    TimeManager.setCurrentTime(12, 21);
    registry.removePassedTrainDepartures();
    assertEquals(0, registry.getTrainDepartureSorted().size());
    TimeManager.resetTime();
  }

  @Test
  public void testGetTrainDepartureSorted() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    registry.addTrainDeparture(31, 12, 30, "F4", "Bergen");
    registry.addTrainDeparture(32, 12, 10, "F4", "Bergen");
    assertEquals(3, registry.getTrainDepartureSorted().size());
    assertEquals(32, registry.getTrainDepartureSorted().get(0).getTrainNumber());
    assertEquals(30, registry.getTrainDepartureSorted().get(1).getTrainNumber());
    assertEquals(31, registry.getTrainDepartureSorted().get(2).getTrainNumber());
  }

  @Test
  public void testAssignTrack() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    registry.assignTrack(30, 1);
    assertEquals(1, registry.getTrainDepartureSorted().get(0).getTrack());
  }

  @Test
  public void testAssignTrackTrainNumberDoesNotExist() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    assertThrows(IllegalArgumentException.class, () -> registry.assignTrack(30, 1));
  }

  @Test
  public void testAssignTrackTrackIsAlreadyAssigned() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    registry.addTrainDeparture(31, 12, 30, "F4", "Bergen");
    registry.assignTrack(30, 1);
    assertThrows(IllegalArgumentException.class, () -> registry.assignTrack(31, 1));
  }

  @Test
  public void testAssignDelay() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    registry.addTrainDeparture(30, 12, 20, "F4", "Bergen");
    registry.setDelay(30, 1, 0);
    assertEquals(1, registry.getTrainDepartureSorted().get(0).getDelay().getHour());
    assertEquals(0, registry.getTrainDepartureSorted().get(0).getDelay().getMinute());
  }

  @Test
  public void testAssignDelayTrainNumberDoesNotExist() {
    TrainDepartureRegistry registry = new TrainDepartureRegistry();
    assertThrows(IllegalArgumentException.class, () -> registry.setDelay(30, 1, 0));
  }
}
