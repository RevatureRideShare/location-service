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

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class HousingLocationServiceImplIntegrationTest {

  private HousingLocationServiceImpl housingLocationServiceImpl;
  private HousingLocation existingHousingLocation;
  private HousingLocation nullHousingLocation;
  private HousingLocation nonExistingHousingLocation;
  private HousingLocation updatedHousingLocation;
  private HousingLocation changedHousingLocation;
  private TrainingLocation existingTrainingLocation;
  private TrainingLocation nullTrainingLocation;
  private TrainingLocation emptyTrainingLocation;
  private HousingLocation newHousingLocation;
  private HousingLocation badFormatAddress1HousingLocation;
  private HousingLocation badFormatCityHousingLocation;
  private HousingLocation badFormatCityMaxSizeHousingLocation;
  private HousingLocation badFormatStateHousingLocation;
  private HousingLocation badFormatStateMaxSizeHousingLocation;
  private HousingLocation badFormatZipcodeHousingLocation;
  private HousingLocation badFormatZipcodeMaxSizeHousingLocation;
  private HousingLocation badFormatHousingLocationNameHousingLocation;
  private HousingLocation housingLocationWithNullTrainingLocation;
  private HousingLocation housingLocationWithEmptyTrainingLocation;

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
    nullTrainingLocation = null;
    emptyTrainingLocation = new TrainingLocation(3, "");
    existingTrainingLocation = new TrainingLocation(1, "Existing Location");
    existingHousingLocation = new HousingLocation(1, "13452 BB Downs", "", "Tampa", "Florida",
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
    badFormatCityHousingLocation = new HousingLocation(4, "13452 BB Downs", "", "", "Florida",
        "34116", "Bonita Heights", existingTrainingLocation);
    badFormatCityMaxSizeHousingLocation = new HousingLocation(4, "13452 BB Downs", "",
        "ThisIsARidiculouslyLargeCityItsAbsurdlyLargeNoIdeaHowBigThisCouldGetButItNeedsToPass50",
        "Florida", "34116", "Bonita Heights", existingTrainingLocation);
    badFormatStateHousingLocation = new HousingLocation(4, "13452 BB Downs", "", "Tampa", "",
        "34116", "Bonita Heights", existingTrainingLocation);
    badFormatStateMaxSizeHousingLocation = new HousingLocation(4, "13452 BB Downs", "", "Tampa",
        "ThisIsARidiculouslyLargeStateItsAbsurdlyLargeNoIdeaHowBigThisCouldGetButItNeedsToPass50",
        "34116", "Bonita Heights", existingTrainingLocation);
    badFormatZipcodeHousingLocation = new HousingLocation(4, "13452 BB Downs", "", "Tampa",
        "Florida", "", "Bonita Heights", existingTrainingLocation);
    badFormatZipcodeMaxSizeHousingLocation = new HousingLocation(4, "13452 BB Downs", "", "Tampa",
        "Florida", "403392049450596655444", "Bonita Heights", existingTrainingLocation);
    badFormatHousingLocationNameHousingLocation = new HousingLocation(4, "13452 BB Downs", "",
        "Tampa", "Florida", "34116", "", existingTrainingLocation);
    housingLocationWithNullTrainingLocation = new HousingLocation(4, "13452 BB Downs", "", "Tampa",
        "Florida", "34116", "Bonita Heights", nullTrainingLocation);
    housingLocationWithEmptyTrainingLocation = new HousingLocation(4, "13452 BB Downs", "", "Tampa",
        "Florida", "34116", "Bonita Heights", emptyTrainingLocation);
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
  @Sql("housing-location-script.sql")
  void testGetExistingHousingLocationById() {
    assertEquals(
        housingLocationServiceImpl.getHousingLocation(existingHousingLocation.getLocationID()),
        Optional.of(existingHousingLocation));
  }

  @Test
  @Sql("housing-location-script.sql")
  void testGetAllHousingLocation() {
    List<HousingLocation> existingHLocationList = new ArrayList<>();
    existingHLocationList.add(existingHousingLocation);
    existingHLocationList.add(updatedHousingLocation);
    assertEquals(housingLocationServiceImpl.getAllHousingLocations(), existingHLocationList);
    System.out.println(housingLocationServiceImpl.getAllHousingLocations());
  }

  @Test
  @Sql("housing-location-script.sql")
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
  @Sql("housing-location-script.sql")
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
  @Sql("housing-location-script.sql")
  void testDeleteExistingHousingLocation() {
    housingLocationServiceImpl.deleteHousingLocation(existingHousingLocation);
    assertEquals(
        housingLocationServiceImpl.getHousingLocation(existingHousingLocation.getLocationID()),
        Optional.empty());
  }

  @Test
  @Sql("housing-location-script.sql")
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
  void testCreateBadFormatAddress1HousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatAddress1HousingLocation);
    });
  }

  @Test
  void testCreateBadFormatCityHousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatCityHousingLocation);
    });
  }

  @Test
  void testCreateBadFormatCityMaxSizeHousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatCityMaxSizeHousingLocation);
    });

  }

  @Test
  void testCreateBadFormatStateHousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatStateHousingLocation);
    });
  }

  @Test
  void testCreateBadFormatStateMaxSizeHousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatStateMaxSizeHousingLocation);
    });
  }

  @Test
  void testCreateBadFormatZipcodeHousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatZipcodeHousingLocation);
    });
  }

  @Test
  void testCreateBadFormatZipcodemaxSizeHousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatZipcodeMaxSizeHousingLocation);
    });
  }

  @Test
  void testCreateBadFormatHousingLocationNameHousingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatHousingLocationNameHousingLocation);
    });
  }

  @Test
  void testCreateHousingLocationWithNullTrainingLocation() {
    assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(housingLocationWithNullTrainingLocation);
    });
  }

  @Test
  void testCreateHousingLocationWithEmptyTrainingLocation() {
    assertThrows(JpaObjectRetrievalFailureException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(housingLocationWithEmptyTrainingLocation);
    });
  }

  @Test
  @Sql("housing-location-script.sql")
  void testGetAllHousingLocationsByTrainingLocationId() {
    List<HousingLocation> existingHLocationList = new ArrayList<>();
    existingHLocationList.add(existingHousingLocation);
    existingHLocationList.add(updatedHousingLocation);
    assertEquals(housingLocationServiceImpl.getHousingLocation_TrainingLocation(
        existingTrainingLocation.getTrainingLocationID()), existingHLocationList);
    System.out.println(housingLocationServiceImpl
        .getHousingLocation_TrainingLocation(existingTrainingLocation.getTrainingLocationID()));

  }
}
