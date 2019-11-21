package com.revature.repo;

import com.revature.bean.HousingLocation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HousingLocationRepo extends JpaRepository<HousingLocation, UUID> {

}
