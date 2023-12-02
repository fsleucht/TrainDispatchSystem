package edu.ntnu.stud.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This holds a registry of TrainDeparture objects.
 *
 * <p>It stores information about all TrainDeparture objects in a HashMap.
 * It has methods for adding, searching and sorting TrainDeparture objects.
 */
public class TrainDepartureRegistry {
  private final HashMap<Integer, TrainDeparture> trainDepartureMap;

  public TrainDepartureRegistry() {
    trainDepartureMap = new HashMap<>();
  }

  /**
   * Creates a new TrainDeparture object and adds it to the registry.
   * Does only add the TrainDeparture object if the train number does not already exist.
   *
   * @param trainNumber the number of the train
   * @param hours the hour of departure
   * @param minutes the minute of departure
   * @param line the line the train operates on
   * @param destination the destination of the train
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
   * If no TrainDeparture object is found, an empty Optional is returned.
   *
   * @param trainNumber the number of the train
   * @return an Optional containing the TrainDeparture object with the given train number.
   */
  public List<TrainDeparture> searchTrainDeparture(int trainNumber) {
    return trainDepartureMap.values().stream()
        .filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
        .collect(Collectors.toList());
  }

  /**
   * Searches for TrainDeparture objects with the given Destination.
   * If the destination is not found, an empty list is returned.
   *
   * @param destination the destination of the train
   * @return a list of TrainDeparture objects with the given destination
   */
  public List<TrainDeparture> searchTrainDepartureDestination(String destination) {
    return trainDepartureMap.values().stream()
        .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .collect(Collectors.toList());
  }

  /**
   * Removes all TrainDeparture objects with a departure time before the current time.
   */
  public void removePassedTrainDepartures() {
    trainDepartureMap.values().removeIf(
        trainDeparture -> trainDeparture.getDepartureTime().isBefore(TimeManager.getCurrentTime()));
  }

  /**
   * Sorts all TrainDeparture objects by departure time.
   *
   * @return a list of all TrainDeparture objects sorted by departure time
   */
  public List<TrainDeparture> getTrainDepartureSorted() {
    removePassedTrainDepartures();
    return trainDepartureMap.values().stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .collect(Collectors.toList());
  }

  public void assignTrack(int trainNumber, int track) throws IllegalArgumentException {
    if (!trainDepartureMap.containsKey(trainNumber)) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    TrainDeparture trainDeparture = trainDepartureMap.get(trainNumber);
    trainDeparture.setTrack(track);
  }

  public void assignDelay(int trainNumber, int hours, int minutes) throws IllegalArgumentException {
    if (!trainDepartureMap.containsKey(trainNumber)) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    TrainDeparture trainDeparture = trainDepartureMap.get(trainNumber);
    trainDeparture.setDelay(hours, minutes);
  }
}
