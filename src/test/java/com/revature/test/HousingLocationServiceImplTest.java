package com.revature.test;

import static org.junit.jupiter.api.Assertions.fail;

import com.revature.bean.HousingLocation;
import com.revature.repo.HousingLocationRepo;
import com.revature.service.HousingLocationServiceImpl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HousingLocationServiceImplTest {

  @Mock
  private HousingLocationRepo housinglocationRepo;

  @InjectMocks
  private HousingLocationServiceImpl housingLocationServiceImpl = new HousingLocationServiceImpl();

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
    // newHousingLocation = new HousingLocation(_, "123 New Ave", null, "Random City", "Maryland",
    // "20601", "Nice Apartments", );

    // existingHousingLocation = new HousingLocation(_, )
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
