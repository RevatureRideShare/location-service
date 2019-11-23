package com.revature.test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import com.revature.bean.TrainingLocation;
import com.revature.repo.TrainingLocationRepo;
import com.revature.service.TrainingLocationServiceImpl;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
    newTrainingLocation = new TrainingLocation(__, "New Location");

    existingTrainingLocation = new TrainingLocation(__, __);

    nullTrainingLocation = null;

    badFormatTrainingLocation = new TrainingLocation(__, "Bad Formatted Location");

    when(trainingLocationRepo.save(newTrainingLocation)).thenReturn(newTrainingLocation);
    // delete
    when(trainingLocationRepo.findById(newTrainingLocation.getTrainingLocationID()))
        .thenReturn(Optional.empty());


    when(trainingLocationRepo.save(existingTrainingLocation)).thenThrow(___);
    // delete
    when(trainingLocationRepo.findById(existingTrainingLocation.getTrainingLocationID()))
        .thenReturn(Optional.of(existingTrainingLocation));


    /*
     * when(trainingLocationRepo.save(nullTrainingLocation)).thenThrow(IllegalArgumentException.
     * class); // delete
     * when(trainingLocationRepo.findById(nullTrainingLocation.getTrainingLocationID()))
     * .thenThrow(IllegalArgumentException.class);
     * 
     * 
     * when(trainingLocationRepo.save(badFormatTrainingLocation))
     * .thenThrow(ConstraintViolationException.class); // delete
     * when(trainingLocationRepo.findById(badFormatTrainingLocation.getTrainingLocationID()))
     * .thenThrow(___);
     */
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
