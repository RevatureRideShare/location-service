package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.LinkedList;
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
import com.revature.bean.HousingLocation;
import com.revature.bean.TrainingLocation;
import com.revature.exception.DeleteNonexistentException;
import com.revature.exception.UpdateNonexistentException;
import com.revature.repo.HousingLocationRepo;
import com.revature.service.HousingLocationServiceImpl;

@SpringBootTest
class HousingLocationServiceImplTest {

  @Mock
  private HousingLocationRepo housingLocationRepo;

  @InjectMocks
  private HousingLocationServiceImpl housingLocationServiceImpl = new HousingLocationServiceImpl();

  private TrainingLocation existingTrainingLocation;

  private HousingLocation newHousingLocation;

  private HousingLocation existingHousingLocation;

  private HousingLocation nullHousingLocation;

  private HousingLocation badFormatHousingLocation;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    existingTrainingLocation = new TrainingLocation(3, "Existing Location");

    newHousingLocation = new HousingLocation(1, "123 New Ave", null, "Random City", "Maryland",
        "20601", "Nice Apartments", existingTrainingLocation);

    existingHousingLocation = new HousingLocation(2, "12702 Bruce B Downs Blvd", null, "Tampa",
        "Florida", "33612", "IQ Apartments", existingTrainingLocation);

    nullHousingLocation = null;

    badFormatHousingLocation =
        new HousingLocation(4, "", null, "", "", "", "", existingTrainingLocation);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreateNewHousingLocation() {
    when(housingLocationRepo.findById(newHousingLocation.getLocationID()))
        .thenReturn(Optional.empty());
    when(housingLocationRepo.save(newHousingLocation)).thenReturn(newHousingLocation);
    assertEquals(newHousingLocation,
        housingLocationServiceImpl.createHousingLocation(newHousingLocation));
    verify(housingLocationRepo).save(newHousingLocation);
  }

  @Test
  void testCreateExistingHousingLocation() {
    when(housingLocationRepo.findById(existingHousingLocation.getLocationID()))
        .thenReturn(Optional.of(existingHousingLocation));
    Assertions.assertThrows(DuplicateKeyException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(existingHousingLocation);
    });
  }

  @Test
  void testCreateNullHousingLocation() {
    when(housingLocationRepo.save(nullHousingLocation)).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(nullHousingLocation);
    });
  }

  @Test
  void testCreateBadFormatHousingLocation() {
    when(housingLocationRepo.save(badFormatHousingLocation))
        .thenThrow(ConstraintViolationException.class);
    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.createHousingLocation(badFormatHousingLocation);
    });
  }

  @Test
  void testGetNewHousingLocation() {
    when(housingLocationRepo.findById(newHousingLocation.getLocationID()))
        .thenReturn(Optional.empty());
    assertEquals(Optional.empty(),
        housingLocationServiceImpl.getHousingLocation(newHousingLocation.getLocationID()));
  }

  @Test
  void testGetExistingHousingLocation() {
    when(housingLocationRepo.findById(existingHousingLocation.getLocationID()))
        .thenReturn(Optional.of(existingHousingLocation));
    assertEquals(Optional.of(existingHousingLocation),
        housingLocationServiceImpl.getHousingLocation(existingHousingLocation.getLocationID()));
  }

  @Test
  void testGetNullHousingLocation() {
    when(housingLocationRepo.findById(null)).thenThrow(IllegalArgumentException.class);
    Assertions.assertThrows(NullPointerException.class, () -> {
      housingLocationServiceImpl.getHousingLocation(nullHousingLocation.getLocationID());
    });
  }

  @Test
  void testGetBadFormatHousingLocation() {
    when(housingLocationRepo.findById(badFormatHousingLocation.getLocationID()))
        .thenReturn(Optional.empty());
    assertEquals(Optional.empty(),
        housingLocationServiceImpl.getHousingLocation(badFormatHousingLocation.getLocationID()));
  }

  @Test
  void testGetHousingLocationsByTrainingLocation() {
    List<HousingLocation> existingList = new LinkedList<>();
    existingList.add(existingHousingLocation);
    when(housingLocationRepo.findByTrainingLocation_TrainingLocationID(
        existingTrainingLocation.getTrainingLocationID())).thenReturn(existingList);
    assertEquals(existingList, housingLocationServiceImpl
        .getHousingLocationByTrainingLocation(existingTrainingLocation.getTrainingLocationID()));
  }

  @Test
  void testGetAllHousingLocations() {
    List<HousingLocation> existingList = new LinkedList<>();
    existingList.add(existingHousingLocation);
    when(housingLocationRepo.findAll()).thenReturn(existingList);
    assertEquals(existingList, housingLocationServiceImpl.getAllHousingLocations());
  }

  @Test
  void testDeleteNewHousingLocation() {
    when(housingLocationRepo.findById(newHousingLocation.getLocationID()))
        .thenReturn(Optional.empty());
    Assertions.assertThrows(DeleteNonexistentException.class, () -> {
      housingLocationServiceImpl.deleteHousingLocation(newHousingLocation);
    });
  }

  @Test
  void testDeleteExistingHousingLocation() {
    when(housingLocationRepo.findById(existingHousingLocation.getLocationID()))
        .thenReturn(Optional.of(existingHousingLocation));
    List<HousingLocation> existingList = housingLocationServiceImpl.getAllHousingLocations();
    housingLocationServiceImpl.deleteHousingLocation(existingHousingLocation);
    List<HousingLocation> afterDeletingList = housingLocationServiceImpl.getAllHousingLocations();
    existingList.remove(existingHousingLocation);

    assertEquals(existingList, afterDeletingList);
    verify(housingLocationRepo).delete(existingHousingLocation);
  }

  @Test
  void testDeleteNullHousingLocation() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      housingLocationServiceImpl.deleteHousingLocation(nullHousingLocation);
    });
  }

  @Test
  void testDeleteBadFormatHousingLocation() {
    when(housingLocationRepo.findById(badFormatHousingLocation.getLocationID()))
        .thenReturn(Optional.empty());
    Assertions.assertThrows(DeleteNonexistentException.class, () -> {
      housingLocationServiceImpl.deleteHousingLocation(badFormatHousingLocation);
    });
  }

  @Test
  void testUpdateNewHousingLocation() {
    when(housingLocationRepo.findById(newHousingLocation.getLocationID()))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(UpdateNonexistentException.class, () -> {
      housingLocationServiceImpl.updateHousingLocation(newHousingLocation);
    });
  }

  @Test
  void testUpdateExistingHousingLocation() {
    HousingLocation updatedExistingLocation =
        new HousingLocation(2, "12702 Bruce B Downs Blvd", null, "Tampa", "Florida", "33612",
            "New and Improved IQ Apartments", existingTrainingLocation);

    when(housingLocationRepo.findById(updatedExistingLocation.getLocationID()))
        .thenReturn(Optional.of(existingHousingLocation));

    when(housingLocationRepo.save(updatedExistingLocation)).thenReturn(updatedExistingLocation);

    assertEquals(updatedExistingLocation,
        housingLocationServiceImpl.updateHousingLocation(updatedExistingLocation));

    verify(housingLocationRepo).save(updatedExistingLocation);
  }

  @Test
  void testUpdateNullHousingLocation() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      housingLocationServiceImpl.updateHousingLocation(nullHousingLocation);
    });
  }

  @Test
  void testUpdateBadFormatHousingLocation() {
    HousingLocation updatedBadLocation =
        new HousingLocation(existingHousingLocation.getLocationID(), "", null, "Tampa", "Florida",
            "", "IQ Apartments", existingTrainingLocation);

    when(housingLocationRepo.findById(updatedBadLocation.getLocationID()))
        .thenReturn(Optional.of(existingHousingLocation));

    when(housingLocationRepo.save(updatedBadLocation))
        .thenThrow(ConstraintViolationException.class);

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      housingLocationServiceImpl.updateHousingLocation(updatedBadLocation);
    });
  }

  // @Test
  // void dummy() {
  // assertTrue(false);
  // }
}
