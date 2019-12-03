package com.revature.controller;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.trace;

import com.revature.bean.HousingLocation;
import com.revature.service.HousingLocationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class contains all of the controller logic for HousingLocation, as well as calling the
 * appropriate HousingLocationService methods.
 * 
 * @author Jane Shin
 * @author Roberto Rodriguez
 */
@RestController
public class HousingLocationController {
  private HousingLocationService housingLocationService;

  @Autowired
  public void setHousingLocationService(HousingLocationService housingLocationService) {
    this.housingLocationService = housingLocationService;
  }

  /**
   * This method is a RESTful endpoint that allows the creation of a housing location. It returns a
   * ResponseEntity with the HousingLocation passed in and HttpStatus.CREATED if successful. If it
   * faces ConstraintViolationException, DuplicateKeyException, or NullPointerException, it returns
   * a ResponseEntity with an error body containing an appropriate message, and
   * HttpStatus.BAD_REQUEST.
   * 
   * @param housingLocation The housing location being created
   * @return The ResponseEntity object with appropriate body and HTTP status code
   */
  @PostMapping("/housing-location")
  public ResponseEntity<?> createHousingLocation(
      @RequestBody(required = false) HousingLocation housingLocation) {
    trace("Inside of createHousingLocation Service method.");

    HousingLocation newHousingLocation;
    Map<String, Object> error = new HashMap<>();

    try {
      trace("Trying to create a Housing Location.");
      newHousingLocation = housingLocationService.createHousingLocation(housingLocation);
      trace("Returning a created response.");
      return new ResponseEntity<>(newHousingLocation, HttpStatus.CREATED);
    } catch (ConstraintViolationException c) {
      error("ConstraintViolationException has been caught.");
      error.put("message", c.getMessage());
      trace("Returning a bad request.");
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } catch (DuplicateKeyException d) {
      error("DuplicateKeyException has been caught.");
      error.put("message", d.getMessage());
      trace("Returning a bad request.");
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } catch (NullPointerException n) {
      error("NullPointerException has been caught.");
      error.put("message", "Cannot pass in a null HousingLocation object");
      trace("Returning a bad request.");
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * This method is a RESTful endpoint that finds the housing location in the database associated
   * with the given housingID. It returns the ResponseEntity with the HousingLocation passed in and
   * HttpStatus.OK. If there is no such housing location, then it should return just an
   * HttpStatus.NO_CONTENT.
   * 
   * @param housingID The id of the housing location being looked for
   * @return The ResponseEntity object with the housing location and HTTP status code
   */
  @GetMapping("/housing-location/{housingID}")
  public ResponseEntity<HousingLocation> getHousingLocation(
      @PathVariable("housingID") int housingID) {
    trace("Inside getHousingLocation Controller method.");
    Optional<HousingLocation> optHousingLocation =
        housingLocationService.getHousingLocation(housingID);

    if (optHousingLocation.isPresent()) {
      trace("Housing Location is present.");
      trace("Retuning an OK response.");
      return new ResponseEntity<>(optHousingLocation.get(), HttpStatus.OK);
    } else {
      trace("Returning a No Content response.");
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  /**
   * This method is a RESTful endpoint that returns all of the housing locations in the database. It
   * returns a ResponseEntity with that list and HttpStatus.OK.
   * 
   * @return The ResponseEntity object with a list of all housing locations and HTTP status code
   */
  @GetMapping("/housing-location")
  public ResponseEntity<List<HousingLocation>> getAllHousingLocations() {
    trace("Inside getAllHousingLocations Controller method.");
    List<HousingLocation> allHousingLocations = housingLocationService.getAllHousingLocations();
    trace("Returning an OK response.");
    return new ResponseEntity<>(allHousingLocations, HttpStatus.OK);
  }

  /**
   * This method is a RESTful endpoint that returns all of the housing locations associated with a
   * given id of a training location in the database. It returns a ResponseEntity with that list and
   * HttpStatus.OK.
   * 
   * @return The ResponseEntity object with a list of all housing locations associated with the id
   *         of a given training location and HTTP status code
   */
  @GetMapping("/training-location/{trainingLocationID}/housing-location")
  public ResponseEntity<List<HousingLocation>> getHousingLocationsByTrainingLocation(
      @PathVariable("trainingLocationID") int trainingLocationID) {
    trace("Inside the getHosuingLocationsByTrainingLocation Controller method.");
    List<HousingLocation> housingLocations =
        housingLocationService.getHousingLocationByTrainingLocation(trainingLocationID);
    trace("Returning an OK response.");
    return new ResponseEntity<>(housingLocations, HttpStatus.OK);
  }
}
