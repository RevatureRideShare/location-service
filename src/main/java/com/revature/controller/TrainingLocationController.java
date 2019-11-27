package com.revature.controller;

import com.revature.bean.TrainingLocation;
import com.revature.service.TrainingLocationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TrainingLocationController {
  private TrainingLocationService trainingLocationService;

  @Autowired
  public void setTrainingLocationService(TrainingLocationService trainingLocationService) {
    this.trainingLocationService = trainingLocationService;
  }

  @PostMapping("/training-location")
  public ResponseEntity<TrainingLocation> createTrainingLocation(
      @RequestBody TrainingLocation trainingLocation) {
    return null;
  }

  @GetMapping("/training-location")
  public ResponseEntity<List<TrainingLocation>> getAllTrainingLocations() {
    return null;
  }

  @GetMapping("/test/public")
  public String test() {
    return "Success - TrainingLocation Service";
  }
}
