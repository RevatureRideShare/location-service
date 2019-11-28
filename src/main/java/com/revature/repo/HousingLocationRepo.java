package com.revature.repo;

import com.revature.bean.HousingLocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HousingLocationRepo extends JpaRepository<HousingLocation, Integer> {
  public List<HousingLocation> findByTrainingLocation_TrainingLocationID(int trainingLocationID);
}
