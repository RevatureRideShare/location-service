package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.revature.bean.TrainingLocation;
import com.revature.repo.TrainingLocationRepo;
import com.revature.service.TrainingLocationServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@SpringBootTest
class TrainingLocationServiceImplTest {

  @Mock
  private TrainingLocationRepo trainingLocationRepo;

  @InjectMocks
  private TrainingLocationServiceImpl trainingLocationServiceImpl =
      new TrainingLocationServiceImpl();

  private TrainingLocation newTrainingLocation;

  private TrainingLocation existingTrainingLocation;

  private TrainingLocation nullTrainingLocation;

  private TrainingLocation badFormatTrainingLocation;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    newTrainingLocation = new TrainingLocation(1, "New Location");

    existingTrainingLocation = new TrainingLocation(3, "Existing Location");

    nullTrainingLocation = null;

    badFormatTrainingLocation = new TrainingLocation(2, "");
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreateNewTrainingLocation() {
    when(trainingLocationRepo.save(newTrainingLocation)).thenReturn(newTrainingLocation);
    assertEquals(newTrainingLocation,
        trainingLocationServiceImpl.createTrainingLocation(newTrainingLocation));
    verify(trainingLocationRepo).save(newTrainingLocation);
  }

  @Test
  void testCreateExistingTrainingLocation() {
    when(trainingLocationRepo.save(existingTrainingLocation)).thenReturn(existingTrainingLocation);
    Assertions.assertThrows(DuplicateKeyException.class, () -> {
      trainingLocationServiceImpl.createTrainingLocation(existingTrainingLocation);
    });

  }

  @Test
  void testCreateNullTrainingLocation() {
    when(trainingLocationRepo.save(nullTrainingLocation)).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      trainingLocationServiceImpl.createTrainingLocation(nullTrainingLocation);
    });
  }

  @Test
  void testCreateBadFormatTrainingLocation() {
    when(trainingLocationRepo.save(badFormatTrainingLocation))
        .thenThrow(ConstraintViolationException.class);
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      trainingLocationServiceImpl.createTrainingLocation(badFormatTrainingLocation);
    });
  }

  @Test
  void testGetNewTrainingLocation() {
    when(trainingLocationRepo.findById(newTrainingLocation.getTrainingLocationID()))
        .thenReturn(Optional.empty());
    assertEquals(Optional.empty(), trainingLocationServiceImpl
        .getTrainingLocation(newTrainingLocation.getTrainingLocationID()));
  }

  @Test
  void testGetExistingTrainingLocation() {
    when(trainingLocationRepo.findById(existingTrainingLocation.getTrainingLocationID()))
        .thenReturn(Optional.of(existingTrainingLocation));
    assertEquals(Optional.of(existingTrainingLocation), trainingLocationServiceImpl
        .getTrainingLocation(existingTrainingLocation.getTrainingLocationID()));
  }

  @Test
  void testGetNullTrainingLocation() {
    when(trainingLocationRepo.findById(null)).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      trainingLocationServiceImpl.getTrainingLocation(nullTrainingLocation.getTrainingLocationID());
    });
  }

  @Test
  void testGetBadFormatTrainingLocation() {
    when(trainingLocationRepo.findById(badFormatTrainingLocation.getTrainingLocationID()))
        .thenReturn(Optional.empty());
    assertEquals(Optional.empty(), trainingLocationServiceImpl
        .getTrainingLocation(badFormatTrainingLocation.getTrainingLocationID()));
  }

  @Test
  void testGetAllTrainingLocations() {
    List<TrainingLocation> existingTLocationList = new ArrayList<>();
    existingTLocationList.add(existingTrainingLocation);
    System.out.println(trainingLocationServiceImpl.getAllTrainingLocations());
    when(trainingLocationRepo.findAll()).thenReturn(existingTLocationList);
    assertEquals(existingTLocationList, trainingLocationServiceImpl.getAllTrainingLocations());
  }

  @Test
  void testDeleteNewTrainingLocation() {
    List<TrainingLocation> existingList = trainingLocationServiceImpl.getAllTrainingLocations();
    trainingLocationServiceImpl.deleteTrainingLocation(newTrainingLocation);
    List<TrainingLocation> afterDeletingList =
        trainingLocationServiceImpl.getAllTrainingLocations();

    assertEquals(existingList, afterDeletingList);
    verify(trainingLocationRepo).delete(newTrainingLocation);
  }

  @Test
  void testDeleteExistingTrainingLocation() {
    List<TrainingLocation> existingList = trainingLocationServiceImpl.getAllTrainingLocations();
    trainingLocationServiceImpl.deleteTrainingLocation(existingTrainingLocation);
    List<TrainingLocation> afterDeletingList =
        trainingLocationServiceImpl.getAllTrainingLocations();
    existingList.remove(existingTrainingLocation);

    assertEquals(existingList, afterDeletingList);
    verify(trainingLocationRepo).delete(existingTrainingLocation);
  }

  @Test
  void testDeleteNullTrainingLocation() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      trainingLocationServiceImpl.deleteTrainingLocation(nullTrainingLocation);
    });

  }

  @Test
  void testDeleteBadFormatTrainingLocation() {

  }
}
