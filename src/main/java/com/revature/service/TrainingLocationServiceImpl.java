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
import org.springframework.transaction.TransactionSystemException;

/**
 * This class contains all of the business logic for TrainingLocation, as well as calling the
 * appropriate TrainingLocationRepo methods. It implements the TrainingLocationService interface.
 * 
 * @author Jane Shin
 * @author Roberto Rodriguez
 */
@Service
public class TrainingLocationServiceImpl implements TrainingLocationService {

  private TrainingLocationRepo trainingLocationRepo;

  @Autowired
  public void setTrainingLocationRepo(TrainingLocationRepo trainingLocationRepo) {
    this.trainingLocationRepo = trainingLocationRepo;
  }

  /**
   * This method is used for creating a TrainingLocation by taking in a TrainingLocation object. If
   * the id/primary key already exists in the database, then this method throws a
   * DuplicateKeyException. If the TrainingLocation passed in is null, then the method should throw
   * a NullPointerException because you cannot use getTrainingLocationID() on a null object. If any
   * of the bean validation on the TrainingLocation passed in is violated, then the method should
   * throw a ConstraintViolationException.
   */
  @Override
  public TrainingLocation createTrainingLocation(TrainingLocation trainingLocation)
      throws NullPointerException {
    if (getTrainingLocation(trainingLocation.getTrainingLocationID()).isPresent()) {
      throw new DuplicateKeyException("Object already exists in database");
    } else {
      try {
        return trainingLocationRepo.save(trainingLocation);
      } catch (TransactionSystemException t) {
        Throwable myT = t.getCause().getCause();

        if (myT instanceof ConstraintViolationException) {
          throw ((ConstraintViolationException) myT);
        }
        throw t;
      }
    }
  }

  /**
   * This method is used for deleting a TrainingLocation by taking in a TrainingLocation object. If
   * a TrainingLocation with the id/primary key does not exist in the database, then this method
   * throws a custom DeleteNonExistentException. If the TrainingLocation passed in is null, then the
   * method should throw a NullPointerException because you cannot use getTrainingLocationID() on a
   * null object.
   */
  @Override
  public void deleteTrainingLocation(TrainingLocation trainingLocation)
      throws NullPointerException {
    if (!getTrainingLocation(trainingLocation.getTrainingLocationID()).isPresent()) {
      throw new DeleteNonexistentException(
          trainingLocation + " does not exist and cannot be deleted");
    } else {
      trainingLocationRepo.delete(trainingLocation);
    }
  }

  /**
   * This method is used for retrieving a TrainingLocation based on the locationID that is passed
   * in. If a TrainingLocation with the specified id/primary key exists in the database, the method
   * should return an Optional of that object. Otherwise, it returns an empty Optional.
   */
  @Override
  public Optional<TrainingLocation> getTrainingLocation(int trainingLocationID) {
    return trainingLocationRepo.findById(trainingLocationID);
  }

  /**
   * This method is used for updating a TrainingLocation by taking in a TrainingLocation object. If
   * the id/primary key does not exist in the database, then this method throws a custom
   * UpdateNonexistentException. If the TrainingLocation passed in is null, then the method should
   * throw a NullPointerException because you cannot use getTrainingLocationID() on a null object.
   * If any of the bean validation on the TrainingLocation passed in is violated, then the method
   * should expect a ConstraintViolationException from TrainingLocationRepo.
   */
  @Override
  public TrainingLocation updateTrainingLocation(TrainingLocation trainingLocation)
      throws NullPointerException {
    if (!getTrainingLocation(trainingLocation.getTrainingLocationID()).isPresent()) {
      throw new UpdateNonexistentException(
          trainingLocation + " does not exist and cannot be updated");
    } else {
      try {
        return trainingLocationRepo.save(trainingLocation);
      } catch (TransactionSystemException t) {
        Throwable myT = t.getCause().getCause();

        if (myT instanceof ConstraintViolationException) {
          throw ((ConstraintViolationException) myT);
        }
        throw t;
      }
    }
  }

  /**
   * This method is used for retrieving all TrainingLocations in the database.
   */
  @Override
  public List<TrainingLocation> getAllTrainingLocations() {
    return trainingLocationRepo.findAll();
  }

}
