package com.revature.test;

import static org.junit.jupiter.api.Assertions.fail;

import com.revature.bean.HousingLocation;
import com.revature.repo.HousingLocationRepo;
import com.revature.service.HousingLocationServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HousingLocationImplTest {

  @Mock
  HousingLocationRepo housinglocationRepo;

  @InjectMocks
  HousingLocationServiceImpl housingLocationServiceImpl = new HousingLocationServiceImpl();

  HousingLocation newHousingLocation;

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
