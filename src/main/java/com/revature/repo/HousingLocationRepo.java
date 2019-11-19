package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.bean.HousingLocation;

@Repository
public interface HousingLocationRepo extends JpaRepository<HousingLocation, String>  {

}
