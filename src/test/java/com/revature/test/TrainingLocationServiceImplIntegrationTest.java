package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.revature.bean.TrainingLocation;
import com.revature.exception.DeleteNonexistentException;
import com.revature.exception.UpdateNonexistentException;
import com.revature.service.TrainingLocationServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class TrainingLocationServiceImplIntegrationTest {

  private TrainingLocationServiceImpl trainingLocationServiceImpl;
  private TrainingLocation existingTrainingLocation;
  private TrainingLocation updatedTrainingLocation;
  private TrainingLocation nullTrainingLocation;
  private TrainingLocation nonExistingTrainingLocation;
  private TrainingLocation changedTrainingLocation;
  private TrainingLocation newTrainingLocation;

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
    nullTrainingLocation = null;
    existingTrainingLocation = new TrainingLocation(1, "Existing Location");
    updatedTrainingLocation = new TrainingLocation(6, "Its Not Updated");
    nonExistingTrainingLocation = new TrainingLocation(999, "I Dont Exist");
    changedTrainingLocation = new TrainingLocation(6, "Its now updated!");
    newTrainingLocation = new TrainingLocation(2, "New Training Location");
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  @Sql("training-location-script.sql")
  void testGetExistingTrainingLocationById() {
    assertEquals(
        trainingLocationServiceImpl
            .getTrainingLocation(existingTrainingLocation.getTrainingLocationID()),
        Optional.of(existingTrainingLocation));
  }

  @Test
  @Sql("training-location-script.sql")
  void testGetAllTrainingLocation() {
    List<TrainingLocation> existingTLocationList = new ArrayList<>();
    existingTLocationList.add(existingTrainingLocation);
    existingTLocationList.add(updatedTrainingLocation);
    assertEquals(trainingLocationServiceImpl.getAllTrainingLocations(), existingTLocationList);
  }

  @Test
  void testCreateBadFormatTrainingLocation() {
    TrainingLocation badFormatTrainingLocation = new TrainingLocation(5, "");
    assertThrows(ConstraintViolationException.class, () -> {
      trainingLocationServiceImpl.createTrainingLocation(badFormatTrainingLocation);
    });

  }

  @Test
  @Sql("training-location-script.sql")
  void testCreateNewTrainingLocation() {
    TrainingLocation extraNewTraingingLocation =
        trainingLocationServiceImpl.createTrainingLocation(newTrainingLocation);
    assertEquals(Optional.of(extraNewTraingingLocation), trainingLocationServiceImpl
        .getTrainingLocation(extraNewTraingingLocation.getTrainingLocationID()));
  }

  @Test
  @Sql("training-location-script.sql")
  void testCreateExistingTrainingLocation() {
    assertThrows(DuplicateKeyException.class, () -> {
      trainingLocationServiceImpl.createTrainingLocation(existingTrainingLocation);
    });
  }

  @Test
  void testCreateNullTrainingLocation() {
    assertThrows(NullPointerException.class, () -> {
      trainingLocationServiceImpl.createTrainingLocation(nullTrainingLocation);
    });
  }

  @Test
  @Sql("training-location-script.sql")
  void testDeleteExistingTrainingLocation() {
    trainingLocationServiceImpl.deleteTrainingLocation(existingTrainingLocation);
    assertEquals(trainingLocationServiceImpl
        .getTrainingLocation(existingTrainingLocation.getTrainingLocationID()), Optional.empty());
  }

  @Test
  void testDeleteNonExistingTrainingLocation() {
    assertThrows(DeleteNonexistentException.class, () -> {
      trainingLocationServiceImpl.deleteTrainingLocation(nonExistingTrainingLocation);
    });

  }

  @Test
  @Sql("training-location-script.sql")
  void testUpdateExistingTrainingLocation() {
    trainingLocationServiceImpl.updateTrainingLocation(changedTrainingLocation);
    assertEquals(trainingLocationServiceImpl.getTrainingLocation(
        changedTrainingLocation.getTrainingLocationID()), Optional.of(changedTrainingLocation));
  }

  @Test
  void testUpdateNonExistingTrainingLocation() {
    assertThrows(UpdateNonexistentException.class, () -> {
      trainingLocationServiceImpl.updateTrainingLocation(nonExistingTrainingLocation);
    });
  }
}
