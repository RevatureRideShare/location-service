package com.revature.controller;

import com.revature.bean.TrainingLocation;
import com.revature.service.TrainingLocationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class contains all of the controller logic for TrainingLocation, as well as calling the
 * appropriate TrainingLocationService methods.
 * 
 * @author Jane Shin
 * @author Roberto Rodriguez
 */
@RestController
@CrossOrigin
public class TrainingLocationController {
  private TrainingLocationService trainingLocationService;

  @Autowired
  public void setTrainingLocationService(TrainingLocationService trainingLocationService) {
    this.trainingLocationService = trainingLocationService;
  }

  /**
   * This method is a RESTful endpoint that allows the creation of a training location. It returns a
   * ResponseEntity with the TrainingLocation passed in and HttpStatus.CREATED if successful. If it
   * faces ConstraintViolationException, DuplicateKeyException, or NullPointerException, it returns
   * a ResponseEntity with an error body containing an appropriate message, and
   * HttpStatus.BAD_REQUEST.
   * 
   * @param trainingLocation The training location being created
   * @return The ResponseEntity object with appropriate body and HTTP status code
   */
  @PostMapping("/training-location")
  public ResponseEntity<?> createTrainingLocation(
      @RequestBody(required = false) TrainingLocation trainingLocation) {
    TrainingLocation newTrainingLocation;
    Map<String, Object> error = new HashMap<>();

    try {
      newTrainingLocation = trainingLocationService.createTrainingLocation(trainingLocation);
      return new ResponseEntity<>(newTrainingLocation, HttpStatus.CREATED);
    } catch (ConstraintViolationException c) {
      error.put("message", c.getMessage());
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } catch (DuplicateKeyException d) {
      error.put("message", d.getMessage());
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } catch (NullPointerException n) {
      error.put("message", "Cannot pass in a null TrainingLocation object");
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * This method is a RESTful endpoint that returns all of the training locations in the database.
   * It returns a ResponseEntity with that list and HttpStatus.OK.
   * 
   * @return The ResponseEntity object with a list of all training locations and HTTP status code
   */
  @GetMapping("/training-location")
  public ResponseEntity<List<TrainingLocation>> getAllTrainingLocations() {
    List<TrainingLocation> allTrainingLocations = trainingLocationService.getAllTrainingLocations();

    return new ResponseEntity<>(allTrainingLocations, HttpStatus.OK);
  }
}
