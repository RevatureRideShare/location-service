package com.revature.test;

import static org.junit.Assert.assertEquals;

import com.revature.bean.TrainingLocation;
import com.revature.service.TrainingLocationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrainingLocationServiceImplIntegrationTest {

  @Rule
  ExpectedException expectedEx = ExpectedException.none();

  private TrainingLocationServiceImpl trainingLocationServiceImpl;

  private TrainingLocation existingTrainingLocation;

  @Autowired
  public void setTrainingLocationServiceImpl(
      TrainingLocationServiceImpl trainingLocationServiceImpl) {
    this.trainingLocationServiceImpl = trainingLocationServiceImpl;
  }

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {

    existingTrainingLocation = new TrainingLocation(3, "Existing Location");

  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testGetAllTrainingLocation() {
    List<TrainingLocation> existingTLocationList = new ArrayList<>();
    existingTLocationList.add(existingTrainingLocation);
    assertEquals(trainingLocationServiceImpl.getAllTrainingLocations(),
        trainingLocationServiceImpl.getAllTrainingLocations());
    System.out.println(trainingLocationServiceImpl.getAllTrainingLocations());
  }

  @Test
  void testCreateBadFormatTrainingLocation() {
    expectedEx.expect(RollbackException.class);
    expectedEx.expectMessage("Training Location Name cannot be empty!");
    TrainingLocation badFormatTrainingLocation = new TrainingLocation(5, "");
    trainingLocationServiceImpl.createTrainingLocation(badFormatTrainingLocation);
  }

  @Test
  void testCreateNewTrainingLocation() {
    TrainingLocation newTrainingLocation =
        new TrainingLocation(11, "This is the new Training Location");
    assertEquals(trainingLocationServiceImpl.createTrainingLocation(newTrainingLocation),
        newTrainingLocation);
  }
}
