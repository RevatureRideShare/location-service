package com.revature.service;

import com.revature.bean.HousingLocation;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;



public interface HousingLocationService {

  public HousingLocation createHousingLocation(HousingLocation housingLocation)
      throws IllegalArgumentException, ConstraintViolationException;

  public void deleteHousingLocation(HousingLocation housingLocation)
      throws IllegalArgumentException;

  public Optional<HousingLocation> getHousingLocation(int locationID);

  public HousingLocation updateHousingLocation(HousingLocation housingLocation)
      throws IllegalArgumentException, ConstraintViolationException;

  public List<HousingLocation> getAllHousingLocations();
}
