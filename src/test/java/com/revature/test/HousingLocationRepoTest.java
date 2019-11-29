package com.revature.test;

import com.revature.repo.HousingLocationRepo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DataJpaTest
class HousingLocationRepoTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private HousingLocationRepo housingLocationRepo;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {

  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testFindByTrainingLocation() {

  }

}
