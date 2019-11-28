package com.revature.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.TrainingLocation;
import com.revature.controller.TrainingLocationController;
import com.revature.service.TrainingLocationService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class TrainingLocationControllerTest {
  @Mock
  TrainingLocationService trainingLocationService;

  @InjectMocks
  TrainingLocationController trainingLocationController;

  private MockMvc mvc;

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
    MockitoAnnotations.initMocks(this);
    this.mvc = MockMvcBuilders.standaloneSetup(trainingLocationController).build();

    newTrainingLocation = new TrainingLocation(1, "New Location");
    existingTrainingLocation = new TrainingLocation(2, "Existing Location");
    nullTrainingLocation = null;
    badFormatTrainingLocation = new TrainingLocation(3, "");
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreateNewTrainingLocation() throws JsonProcessingException, Exception {
    when(trainingLocationService.createTrainingLocation(newTrainingLocation))
        .thenReturn(newTrainingLocation);
    mvc.perform(
        MockMvcRequestBuilders.post("/training-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(newTrainingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(newTrainingLocation)));
  }

  @Test
  void testCreateExistingTrainingLocation() throws JsonProcessingException, Exception {
    when(trainingLocationService.createTrainingLocation(existingTrainingLocation))
        .thenThrow(new DuplicateKeyException("Object already exists in database"));

    Map<String, Object> error = new HashMap<>();
    error.put("message", "Object already exists in database");

    mvc.perform(
        MockMvcRequestBuilders.post("/training-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(existingTrainingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(error)));
  }

  @Test
  void testCreateNullTrainingLocation() throws JsonProcessingException, Exception {
    when(trainingLocationService.createTrainingLocation(nullTrainingLocation))
        .thenThrow(NullPointerException.class);

    Map<String, Object> error = new HashMap<>();
    error.put("message", "Cannot pass in a null TrainingLocation object");

    mvc.perform(
        MockMvcRequestBuilders.post("/training-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(nullTrainingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(error)));
  }

  @Test
  void testCreateBadFormatTrainingLocation() throws JsonProcessingException, Exception {
    when(trainingLocationService.createTrainingLocation(badFormatTrainingLocation))
        .thenThrow(ConstraintViolationException.class);

    mvc.perform(
        MockMvcRequestBuilders.post("/training-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(badFormatTrainingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetAllTrainingLocations() throws Exception {
    List<TrainingLocation> existingList = new LinkedList<>();
    existingList.add(existingTrainingLocation);

    when(trainingLocationService.getAllTrainingLocations()).thenReturn(existingList);
    mvc.perform(MockMvcRequestBuilders.get("/training-location")).andExpect(status().isOk())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(existingList)));
  }

}
