package com.revature.service;

import com.revature.bean.TrainingLocation;
import com.revature.repo.TrainingLocationRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingLocationServiceImpl implements TrainingLocationService {

  private TrainingLocationRepo trainingLocationRepo;

  @Autowired
  public void setTrainingLocationRepo(TrainingLocationRepo trainingLocationRepo) {
    this.trainingLocationRepo = trainingLocationRepo;
  }

  @Override
  public TrainingLocation createTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException, ConstraintViolationException {
    return trainingLocationRepo.save(trainingLocation);
  }

  @Override
  public void deleteTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException {
    trainingLocationRepo.delete(trainingLocation);
  }

  @Override
  public Optional<TrainingLocation> getTrainingLocation(UUID trainingLocationID) {
    return trainingLocationRepo.findById(trainingLocationID);
  }

  @Override
  public TrainingLocation updateTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException, ConstraintViolationException {
    return trainingLocationRepo.save(trainingLocation);
  }

  @Override
  public List<TrainingLocation> getAllTrainingLocations() {
    return trainingLocationRepo.findAll();
  }

}
