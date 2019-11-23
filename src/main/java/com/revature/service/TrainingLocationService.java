package com.revature.service;

import com.revature.bean.TrainingLocation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

public interface TrainingLocationService {
  public TrainingLocation createTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException, ConstraintViolationException;

  public void deleteTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException;

  public Optional<TrainingLocation> getTrainingLocation(UUID trainingLocationID);

  public TrainingLocation updateTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException, ConstraintViolationException;

  public List<TrainingLocation> getAllTrainingLocations();
}
