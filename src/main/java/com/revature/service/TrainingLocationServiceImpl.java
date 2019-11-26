package com.revature.service;

import com.revature.bean.TrainingLocation;
import com.revature.exception.DeleteNonexistentException;
import com.revature.exception.UpdateNonexistentException;
import com.revature.repo.TrainingLocationRepo;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    if (getTrainingLocation(trainingLocation.getTrainingLocationID()).isPresent()) {
      throw new DuplicateKeyException("Object already exists in database");
    } else {
      return trainingLocationRepo.save(trainingLocation);
    }
  }

  @Override
  public void deleteTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException {
    if (!getTrainingLocation(trainingLocation.getTrainingLocationID()).isPresent()) {
      throw new DeleteNonexistentException(
          trainingLocation + " does not exist and cannot be deleted");
    } else {
      trainingLocationRepo.delete(trainingLocation);
    }
  }

  @Override
  public Optional<TrainingLocation> getTrainingLocation(int trainingLocationID) {
    return trainingLocationRepo.findById(trainingLocationID);
  }

  @Override
  public TrainingLocation updateTrainingLocation(TrainingLocation trainingLocation)
      throws IllegalArgumentException, ConstraintViolationException {
    if (!getTrainingLocation(trainingLocation.getTrainingLocationID()).isPresent()) {
      throw new UpdateNonexistentException(
          trainingLocation + " does not exist and cannot be updated");
    } else {
      return trainingLocationRepo.save(trainingLocation);
    }
  }

  @Override
  public List<TrainingLocation> getAllTrainingLocations() {
    return trainingLocationRepo.findAll();
  }

}
