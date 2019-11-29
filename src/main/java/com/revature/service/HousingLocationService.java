package com.revature.service;

import com.revature.bean.HousingLocation;

import java.util.List;
import java.util.Optional;

public interface HousingLocationService {

  public HousingLocation createHousingLocation(HousingLocation housingLocation)
      throws NullPointerException;

  public void deleteHousingLocation(HousingLocation housingLocation) throws NullPointerException;

  public Optional<HousingLocation> getHousingLocation(int locationID);

  public HousingLocation updateHousingLocation(HousingLocation housingLocation)
      throws NullPointerException;

  public List<HousingLocation> getHousingLocation_TrainingLocation(int trainingLocationID);

  public List<HousingLocation> getAllHousingLocations();
}
