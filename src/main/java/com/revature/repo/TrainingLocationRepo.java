package com.revature.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.bean.TrainingLocation;

@Repository
public interface TrainingLocationRepo extends JpaRepository<TrainingLocation, UUID> {

}
