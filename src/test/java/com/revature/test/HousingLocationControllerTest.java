package com.revature.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.HousingLocation;
import com.revature.bean.TrainingLocation;
import com.revature.controller.HousingLocationController;
import com.revature.service.HousingLocationService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
class HousingLocationControllerTest {
  @Mock
  HousingLocationService housingLocationService;

  @InjectMocks
  HousingLocationController housingLocationController;

  private MockMvc mvc;

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
    MockitoAnnotations.initMocks(this);
    this.mvc = MockMvcBuilders.standaloneSetup(housingLocationController).build();

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
  void testCreateNewHousingLocation() throws JsonProcessingException, Exception {
    when(housingLocationService.createHousingLocation(newHousingLocation))
        .thenReturn(newHousingLocation);
    mvc.perform(
        MockMvcRequestBuilders.post("/housing-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(newHousingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(newHousingLocation)));
  }

  @Test
  void testCreateExistingHousingLocation() throws JsonProcessingException, Exception {
    when(housingLocationService.createHousingLocation(existingHousingLocation))
        .thenThrow(new DuplicateKeyException("Object already exists in database"));

    Map<String, Object> error = new HashMap<>();
    error.put("message", "Object already exists in database");

    mvc.perform(
        MockMvcRequestBuilders.post("/housing-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(existingHousingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(error)));
  }

  @Test
  void testCreateNullHousingLocation() throws JsonProcessingException, Exception {
    when(housingLocationService.createHousingLocation(nullHousingLocation))
        .thenThrow(NullPointerException.class);

    Map<String, Object> error = new HashMap<>();
    error.put("message", "Cannot pass in a null HousingLocation object");

    mvc.perform(
        MockMvcRequestBuilders.post("/housing-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(nullHousingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(error)));
  }

  @Test
  void testCreateBadFormatHousingLocation() throws JsonProcessingException, Exception {
    when(housingLocationService.createHousingLocation(badFormatHousingLocation))
        .thenThrow(ConstraintViolationException.class);

    mvc.perform(
        MockMvcRequestBuilders.post("/housing-location").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(badFormatHousingLocation))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetHousingLocationsByTrainingLocation() throws Exception {
    List<HousingLocation> existingList = new LinkedList<>();
    existingList.add(existingHousingLocation);

    when(housingLocationService
        .getHousingLocationByTrainingLocation(existingTrainingLocation.getTrainingLocationID()))
            .thenReturn(existingList);
    mvc.perform(
        MockMvcRequestBuilders.get("/training-location/{trainingLocationID}/housing-location",
            existingTrainingLocation.getTrainingLocationID()))
        .andExpect(status().isOk())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(existingList)));
  }

  @Test
  void testGetNewHousingLocation() throws Exception {
    when(housingLocationService.getHousingLocation(newHousingLocation.getLocationID()))
        .thenReturn(Optional.empty());

    mvc.perform(MockMvcRequestBuilders.get("/housing-location/{housingLocationID}",
        newHousingLocation.getLocationID())).andExpect(status().isNoContent());
  }

  @Test
  void testGetExistingHousingLocation() throws Exception {
    when(housingLocationService.getHousingLocation(existingHousingLocation.getLocationID()))
        .thenReturn(Optional.of(existingHousingLocation));

    mvc.perform(MockMvcRequestBuilders.get("/housing-location/{housingLocationID}",
        existingHousingLocation.getLocationID())).andExpect(status().isOk())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(existingHousingLocation)));
  }

  @Test
  void testGetAllHousingLocations() throws Exception {
    List<HousingLocation> existingList = new LinkedList<>();
    existingList.add(existingHousingLocation);

    when(housingLocationService.getAllHousingLocations()).thenReturn(existingList);
    mvc.perform(MockMvcRequestBuilders.get("/housing-location")).andExpect(status().isOk())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(existingList)));
  }


}
