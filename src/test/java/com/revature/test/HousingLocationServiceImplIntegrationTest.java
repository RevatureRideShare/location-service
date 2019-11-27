package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.revature.bean.HousingLocation;
import com.revature.bean.TrainingLocation;
import com.revature.exception.DeleteNonexistentException;
import com.revature.exception.UpdateNonexistentException;
import com.revature.service.HousingLocationServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.TransactionSystemException;

@SpringBootTest
class HousingLocationServiceImplIntegrationTest {

  private HousingLocationServiceImpl housingLocationServiceImpl;
  private HousingLocation existingHousingLocation;
  private HousingLocation nullHousingLocation;
  private HousingLocation nonExistingHousingLocation;
  private HousingLocation updatedHousingLocation;
  private HousingLocation changedHousingLocation;
  private TrainingLocation existingTrainingLocation;
  private HousingLocation newHousingLocation;
  private HousingLocation badFormatAddress1HousingLocation;

  @Autowired
  public void setHousingLocationServiceImpl(HousingLocationServiceImpl housingLocationServiceImpl) {
    this.housingLocationServiceImpl = housingLocationServiceImpl;
  }

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    nullHousingLocation = null;
    existingTrainingLocation = new TrainingLocation(3, "Existing Location");
    existingHousingLocation = new HousingLocation(5, "13452 BB Downs", "", "Tampa", "Florida",
        "34116", "The Standard", existingTrainingLocation);
    newHousingLocation = new HousingLocation(2, "65843 Magnolia Drive", "", "Tampa", "Florida",
        "34116", "Exceptional Apartments", existingTrainingLocation);
    nonExistingHousingLocation = new HousingLocation(999, "65843 Magnolia Drive", "", "Tampa",
        "Florida", "34116", "Exceptional Apartments", existingTrainingLocation);
    updatedHousingLocation = new HousingLocation(3, "13452 BB Downs", "", "Tampa", "Florida",
        "34116", "Crossing Swords", existingTrainingLocation);
    changedHousingLocation = new HousingLocation(3, "13452 BB Downs", "", "Tampa", "Florida",
        "34116", "Bonita Heights", existingTrainingLocation);
    badFormatAddress1HousingLocation = new HousingLocation(4, "", "", "Tampa", "Florida", "34116",
        "Bonita Heights", existingTrainingLocation);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreatNullHousingLocation() {
    assertThrows(NullPointerException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(nullHousingLocation);
    });
  }

  @Test
  void testGetExistingHousingLocationById() {
    assertEquals(
        housingLocationServiceImpl.getHousingLocation(existingHousingLocation.getLocationID()),
        Optional.of(existingHousingLocation));

  }

  @Test
  void testGetAllHousingLocation() {
    List<HousingLocation> existingHLocationList = new ArrayList<>();
    existingHLocationList.add(existingHousingLocation);
    assertEquals(housingLocationServiceImpl.getAllHousingLocations(), existingHLocationList);
    System.out.println(housingLocationServiceImpl.getAllHousingLocations());
  }

  @Test
  void testCreateNewHousingLocation() {
    HousingLocation extraNewHousingLocation =
        housingLocationServiceImpl.createHousingLocation(newHousingLocation);
    assertEquals(Optional.of(extraNewHousingLocation),
        housingLocationServiceImpl.getHousingLocation(extraNewHousingLocation.getLocationID()));
  }

  @Test
  void testCreateNullHousingLocation() {
    assertThrows(NullPointerException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(nullHousingLocation);
    });
  }

  @Test
  void testCreateExistingHousingLocation() {
    assertThrows(DuplicateKeyException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(existingHousingLocation);
    });
  }

  @Test
  void testDeleteNonExistingHousingLocation() {
    assertThrows(DeleteNonexistentException.class, () -> {
      housingLocationServiceImpl.deleteHousingLocation(nonExistingHousingLocation);
    });
  }

  @Test
  void testDeleteExistingHousingLocation() {
    housingLocationServiceImpl.deleteHousingLocation(existingHousingLocation);
    assertEquals(
        housingLocationServiceImpl.getHousingLocation(existingHousingLocation.getLocationID()),
        Optional.empty());
  }

  @Test
  void testUpdateExistingHousingLocation() {
    System.out.println("Current state of updatedHousingLocation"
        + housingLocationServiceImpl.getHousingLocation(updatedHousingLocation.getLocationID()));
    housingLocationServiceImpl.updateHousingLocation(changedHousingLocation);
    assertEquals(
        housingLocationServiceImpl.getHousingLocation(changedHousingLocation.getLocationID()),
        Optional.of(changedHousingLocation));
  }

  @Test
  void testUpdateNonExistingHousingLocation() {
    assertThrows(UpdateNonexistentException.class, () -> {
      housingLocationServiceImpl.updateHousingLocation(nonExistingHousingLocation);
    });
  }

  @Test
  void testCreateBadFormatAddress1() {
    assertThrows(TransactionSystemException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatAddress1HousingLocation);
    });
  }

}
