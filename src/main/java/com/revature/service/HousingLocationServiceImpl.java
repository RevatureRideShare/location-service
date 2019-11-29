package com.revature.service;

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
 * @author Erik Haklar
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
    if (getHousingLocation(housingLocation.getLocationID()).isPresent()) {
      throw new DuplicateKeyException("Object already exists in database");
    } else {
      try {
        return housingLocationRepo.save(housingLocation);
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
   * This method is used for deleting a HousingLocation by taking in a HousingLocation object. If a
   * HousingLocation with the id/primary key does not exist in the database, then this method throws
   * a custom DeleteNonExistentException. If the HousingLocation passed in is null, then the method
   * should throw a NullPointerException because you cannot use getLocationID() on a null object.
   */
  @Override
  public void deleteHousingLocation(HousingLocation housingLocation) throws NullPointerException {
    if (!getHousingLocation(housingLocation.getLocationID()).isPresent()) {
      throw new DeleteNonexistentException(
          housingLocation + " does not exist and cannot be deleted");
    } else {
      housingLocationRepo.delete(housingLocation);
    }
  }

  /**
   * This method is used for retrieving a HousingLocation based on the locationID that is passed in.
   * If a HousingLocation with the specified id/primary key exists in the database, the method
   * should return an Optional of that object. Otherwise, it returns an empty Optional.
   */
  @Override
  public Optional<HousingLocation> getHousingLocation(int locationID) {
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
    if (!getHousingLocation(housingLocation.getLocationID()).isPresent()) {
      throw new UpdateNonexistentException(
          housingLocation + " does not exist and cannot be updated");
    } else {
      try {
        return housingLocationRepo.save(housingLocation);
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
   * This method is used for retrieving all HousingLocations associated with the given
   * TrainingLocation in the database.
   */
  @Override
  public List<HousingLocation> getHousingLocationsByTrainingLocation(int trainingLocationID) {
    return housingLocationRepo.findByTrainingLocation_TrainingLocationID(trainingLocationID);
  }

  /**
   * This method is used for retrieving all HousingLocations in the database.
   */
  @Override
  public List<HousingLocation> getAllHousingLocations() {
    return housingLocationRepo.findAll();
  }
}
