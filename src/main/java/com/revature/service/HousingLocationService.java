package com.revature.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.revature.bean.HousingLocation;

public interface HousingLocationService {

	public HousingLocation createHousingLocation(HousingLocation housingLocation);
	
	public void deleteHousingLocation(HousingLocation housingLocation);
	
	public Optional<HousingLocation> getHousingLocation(UUID locationID);
	
	public HousingLocation updateHousingLocation(HousingLocation housingLocation);
	
	public List<HousingLocation> getAllHousingLocations();
}
