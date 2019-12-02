package com.revature.service;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.trace;

import com.revature.bean.HousingLocation;
import com.revature.exception.DeleteNonexistentException;
import com.revature.exception.UpdateNonexistentException;
import com.revature.repo.HousingLocationRepo;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

/**
 * This class contains all of the business logic for HousingLocation, as well as calling the
 * appropriate HousingLocationRepo methods. It implements the HousingLocationService interface.
 * 
 * @author Jane Shin
 * @author Roberto Rodriguez
 */
@Service
public class HousingLocationServiceImpl implements HousingLocationService {

  private HousingLocationRepo housingLocationRepo;

  @Autowired
  public void setHousingLocationrepo(HousingLocationRepo housingLocationRepo) {
    this.housingLocationRepo = housingLocationRepo;
  }

  /**
   * This method is used for creating a HousingLocation by taking in a HousingLocation object. If
   * the id/primary key already exists in the database, then this method throws a
   * DuplicateKeyException. If the HousingLocation passed in is null, then the method should throw a
   * NullPointerException because you cannot use getLocationID() on a null object. If any of the
   * bean validation on the HousingLocation passed in is violated, then the method should expect a
   * ConstraintViolationException from HousingLocationRepo.
   */
  @Override
  public HousingLocation createHousingLocation(HousingLocation housingLocation)
      throws NullPointerException {
    trace("Inside of the createHousingLocation Service method.");
    if (getHousingLocation(housingLocation.getLocationID()).isPresent()) {
      throw new DuplicateKeyException("Object already exists in database");
    } else {
      try {
        trace("Housing Location has been created.");
        return housingLocationRepo.save(housingLocation);

      } catch (TransactionSystemException t) {
        error("A TransactionSystemException has been caught.");
        Throwable myT = t.getCause().getCause();

        if (myT instanceof ConstraintViolationException) {
          error("A ConstraintViolationException has been found.");
          throw ((ConstraintViolationException) myT);
        }
        throw t;
      }
    }
  }

  /**
   * This method is used for deleting a HousingLocation by taking in a HousingLocation object. If a
   * HousingLocation with the id/primary key does not exist in the database, then this method throws
   * a custom DeleteNonExistentException. If the HousingLocation passed in is null, then the method
   * should throw a NullPointerException because you cannot use getLocationID() on a null object.
   */
  @Override
  public void deleteHousingLocation(HousingLocation housingLocation) throws NullPointerException {
    trace("Inside of deleteHousingLocation Service method.");
    if (!getHousingLocation(housingLocation.getLocationID()).isPresent()) {
      error("DeleteNonexistentException has been thrown.");
      throw new DeleteNonexistentException(
          housingLocation + " does not exist and cannot be deleted");
    } else {
      housingLocationRepo.delete(housingLocation);
      trace("Housing Location has been deleted.");
    }
  }

  /**
   * This method is used for retrieving a HousingLocation based on the locationID that is passed in.
   * If a HousingLocation with the specified id/primary key exists in the database, the method
   * should return an Optional of that object. Otherwise, it returns an empty Optional.
   */
  @Override
  public Optional<HousingLocation> getHousingLocation(int locationID) {
    trace("Inside getHousingLocation Service method.");
    trace("Returning a Housing Location by the Id");
    return housingLocationRepo.findById(locationID);
  }

  /**
   * This method is used for updating a HousingLocation by taking in a HousingLocation object. If
   * the id/primary key does not exist in the database, then this method throws a custom
   * UpdateNonexistentException. If the HousingLocation passed in is null, then the method should
   * throw a NullPointerException because you cannot use getLocationID() on a null object. If any of
   * the bean validation on the HousingLocation passed in is violated, then the method should expect
   * a ConstraintViolationException from HousingLocationRepo.
   */
  @Override
  public HousingLocation updateHousingLocation(HousingLocation housingLocation)
      throws NullPointerException {
    trace("Inside of updateHousingLocation Service method.");
    if (!getHousingLocation(housingLocation.getLocationID()).isPresent()) {
      error("DeleteNonexistentException has been thrown.");
      throw new UpdateNonexistentException(
          housingLocation + " does not exist and cannot be updated");
    } else {
      try {
        trace("Housing Location is being updated.");
        return housingLocationRepo.save(housingLocation);
      } catch (TransactionSystemException t) {
        error("A TransactionSystemException has been caught.");
        Throwable myT = t.getCause().getCause();

        if (myT instanceof ConstraintViolationException) {
          error("A ConstraintViolationException has been found.");
          throw ((ConstraintViolationException) myT);
        }
        throw t;
      }
    }
  }

  /**
   * This method is used for retrieving all HousingLocations associated with the given
   * TrainingLocation in the database.
   */
  @Override
  public List<HousingLocation> getHousingLocationByTrainingLocation(int trainingLocationID) {
    trace("Inside of getHousingLocationByTrainingLocation Service method.");
    trace("Returning a list of Housing Locations by Training Location Id");
    return housingLocationRepo.findByTrainingLocation_TrainingLocationID(trainingLocationID);
  }

  /**
   * This method is used for retrieving all HousingLocations in the database.
   */
  @Override
  public List<HousingLocation> getAllHousingLocations() {
    trace("Inside getAllHousingLocations Service method.");
    trace("Returning a list of Housing Locations");
    return housingLocationRepo.findAll();
  }
}
