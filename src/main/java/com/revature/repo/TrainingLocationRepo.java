package com.revature.repo;

import com.revature.bean.TrainingLocation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingLocationRepo extends JpaRepository<TrainingLocation, UUID> {

}
