package com.revature.service;

import com.revature.bean.HousingLocation;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface HousingLocationService {

  public HousingLocation createHousingLocation(HousingLocation housingLocation);

  public void deleteHousingLocation(HousingLocation housingLocation);

  public Optional<HousingLocation> getHousingLocation(UUID locationID);

  public HousingLocation updateHousingLocation(HousingLocation housingLocation);

  public List<HousingLocation> getAllHousingLocations();
}
