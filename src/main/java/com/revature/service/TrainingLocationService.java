package com.revature.service;

import com.revature.bean.TrainingLocation;

import java.util.List;
import java.util.Optional;

public interface TrainingLocationService {
  public TrainingLocation createTrainingLocation(TrainingLocation trainingLocation)
      throws NullPointerException;

  public void deleteTrainingLocation(TrainingLocation trainingLocation) throws NullPointerException;

  public Optional<TrainingLocation> getTrainingLocation(int trainingLocationID);

  public TrainingLocation updateTrainingLocation(TrainingLocation trainingLocation)
      throws NullPointerException;

  public List<TrainingLocation> getAllTrainingLocations();
}
