package com.commute.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commute.db.model.Rides;

@Repository
@Transactional
public interface RideRepository extends JpaRepository<Rides, String>{

	@Query(value="select * from rides where ride_date > ?1", nativeQuery = true)
	List<Rides> getAllRides(String date);

}
