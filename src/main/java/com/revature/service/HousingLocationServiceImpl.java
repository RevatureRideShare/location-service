package com.revature.service;

import com.revature.bean.HousingLocation;
import com.revature.repo.HousingLocationRepo;

import java.util.List;
import java.util.Optional;

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
  public HousingLocation createHousingLocation(HousingLocation housingLocation)
      throws IllegalArgumentException, ConstraintViolationException {
    return housingLocationRepo.save(housingLocation);
  }

  @Override
  public void deleteHousingLocation(HousingLocation housingLocation)
      throws IllegalArgumentException {
    housingLocationRepo.delete(housingLocation);
  }

  @Override
  public Optional<HousingLocation> getHousingLocation(int locationID) {
    return housingLocationRepo.findById(locationID);
  }

  @Override
  public HousingLocation updateHousingLocation(HousingLocation housingLocation)
      throws IllegalArgumentException, ConstraintViolationException {
    return housingLocationRepo.save(housingLocation);
  }

  @Override
  public List<HousingLocation> getAllHousingLocations() {
    return housingLocationRepo.findAll();
  }

}
