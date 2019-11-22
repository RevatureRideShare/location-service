package com.revature.service;

import com.revature.bean.HousingLocation;
import com.revature.repo.HousingLocationRepo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HousingLocationServiceImpl implements HousingLocationService {

  private HousingLocationRepo housingLocationRepo;

  @Autowired
  public void setHousingLocationrepo(HousingLocationRepo housingLocationRepo) {
    this.housingLocationRepo = housingLocationRepo;
  }

  @Override
  public HousingLocation createHousingLocation(HousingLocation housingLocation) {
    try {
      housingLocationRepo.save(housingLocation);
    } catch (IllegalArgumentException e) {

    } catch (ConstraintViolationException c) {

    }
    return housingLocation;
  }

  @Override
  public void deleteHousingLocation(HousingLocation housingLocation) {
    try {
      housingLocationRepo.delete(housingLocation);
    } catch (IllegalArgumentException e) {

    }
  }

  @Override
  public Optional<HousingLocation> getHousingLocation(UUID locationID) {
    return housingLocationRepo.findById(locationID);
  }

  @Override
  public HousingLocation updateHousingLocation(HousingLocation housingLocation) {
    try {
      housingLocationRepo.save(housingLocation);
    } catch (IllegalArgumentException e) {

    } catch (ConstraintViolationException c) {

    }
    return housingLocation;
  }

  @Override
  public List<HousingLocation> getAllHousingLocations() {
    return null;
  }

}
