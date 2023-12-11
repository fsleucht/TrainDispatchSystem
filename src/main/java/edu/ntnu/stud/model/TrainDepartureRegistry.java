package edu.ntnu.stud.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This holds a registry of TrainDeparture objects.
 *
 * <p>It stores information about all TrainDeparture objects in a HashMap.
 * It has methods for adding new departures, searching for departures objects by
 * train number or destination, removing passed departures, getting a sorted list of
 * all departures as well as assigning track and delay to a departure.
 *
 * <p>Created with help from Copilot.
 */
public class TrainDepartureRegistry {
  private final HashMap<Integer, TrainDeparture> trainDepartureMap;

  /**
   * Constructs a TrainDepartureRegistry object and creates a new HashMap.
   */
  public TrainDepartureRegistry() {
    trainDepartureMap = new HashMap<>();
  }

  /**
   * Creates a new TrainDeparture object and adds it to the registry.
   * Does only add the TrainDeparture object if the train number does not already exist in the
   * registry. If the train number already exists, an IllegalArgumentException is thrown.
   *
   * <p>The TrainDeparture object is put in the HashMap with the train number as key.
   *
   * @param trainNumber the number of the departure
   * @param hours the hour of departure
   * @param minutes the minute of departure
   * @param line the line the departure operates on
   * @param destination the destination of the departure
   * @throws IllegalArgumentException if the train number already exists.
   */
  public void addTrainDeparture(
      int trainNumber, int hours, int minutes, String line, String destination)
      throws IllegalArgumentException {
    if (trainDepartureMap.containsKey(trainNumber)) {
      throw new IllegalArgumentException("Train number already exists");
    }
    TrainDeparture trainDeparture = new TrainDeparture(
            trainNumber, hours, minutes, line, destination);
    trainDepartureMap.put(trainNumber, trainDeparture);
  }

  /**
   * Searches for a TrainDeparture object with the given train number.
   * If the train number is not found, an IllegalArgumentException is thrown.
   *
   * @param trainNumber the number of the train
   * @return a List containing the TrainDeparture object with the given train number
   * @throws IllegalArgumentException if the train number does not exist
   */
  public List<TrainDeparture> searchTrainDeparture(int trainNumber)
      throws IllegalArgumentException {
    if (!trainDepartureMap.containsKey(trainNumber)) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    return trainDepartureMap.values().stream()
        .filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
        .collect(Collectors.toList());
  }

  /**
   * Searches for TrainDeparture objects with the given Destination.
   * If the destination is not found, an IllegalArgumentException is thrown.
   *
   * @param destination the destination of the train
   * @return a list of TrainDeparture objects with the given destination
   * @throws IllegalArgumentException if the destination is null or empty or
   *                                  if the destination does not exist
   */
  public List<TrainDeparture> searchTrainDepartureDestination(String destination)
      throws IllegalArgumentException {
    if (destination == null || destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be null or empty");
    }
    if (trainDepartureMap.values().stream()
        .noneMatch(trainDeparture -> trainDeparture.getDestination().equals(destination))) {
      throw new IllegalArgumentException("Destination does not exist");
    }
    return trainDepartureMap.values().stream()
        .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .collect(Collectors.toList());
  }

  /**
   * Removes all TrainDeparture objects with a departure time before the current time from the
   * registry. This method is called every time the current time is updated.
   */
  public void removePassedTrainDepartures() {
    trainDepartureMap.values().removeIf(
        trainDeparture -> trainDeparture.getDepartureTime().isBefore(TimeManager.getCurrentTime()));
  }

  /**
   * Sorts all TrainDeparture objects by departure time. If there are no TrainDeparture objects
   * in the registry, an empty list is returned.
   *
   * @return a list of all TrainDeparture objects sorted by departure time
   */
  public List<TrainDeparture> getTrainDepartureSorted() {
    return trainDepartureMap.values().stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .collect(Collectors.toList());
  }

  /**
   * Assigns a track to a TrainDeparture object by searching for the train number in the registry
   * and calling the setTrack method in the TrainDeparture class on that TrainDeparture object.
   *
   * @param trainNumber the number of the train
   * @param track the track to be assigned
   * @throws IllegalArgumentException if the train number does not exist or
   *                                  if the track is already assigned to another train
   */
  public void assignTrack(int trainNumber, int track) throws IllegalArgumentException {
    if (!trainDepartureMap.containsKey(trainNumber)) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    if (trainDepartureMap.values().stream()
        .anyMatch(trainDeparture -> trainDeparture.getTrack() == track)) {
      throw new IllegalArgumentException("Track is already assigned to another train");
    }
    TrainDeparture trainDeparture = trainDepartureMap.get(trainNumber);
    trainDeparture.setTrack(track);
  }

  /**
   * Sets a delay to a TrainDeparture object by searching for the train number in the registry and
   * calling the setDelay method in the TrainDeparture class on that TrainDeparture object.
   *
   * @param trainNumber the number of the train
   * @param hours hours of the delay to be set
   * @param minutes hours of the delay to be set
   * @throws IllegalArgumentException if the train number does not exist
   */
  public void setDelay(int trainNumber, int hours, int minutes) throws IllegalArgumentException {
    if (!trainDepartureMap.containsKey(trainNumber)) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    TrainDeparture trainDeparture = trainDepartureMap.get(trainNumber);
    trainDeparture.setDelay(hours, minutes);
  }
}
