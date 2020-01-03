package com.commute.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commute.db.model.Rides;

@Repository
@Transactional
public interface RideRepository extends JpaRepository<Rides, String>{

	@Query(value = "select * from rides where ride_date > ?1 and cancelled=0 and available_seats > 0", nativeQuery = true)
	List<Rides> getAllRides(String date);

	@Query(value = "select * from rides where ride_id=?1", nativeQuery = true)
	Rides getRideDetails(int rideId);
	
	@Modifying(clearAutomatically = true)
	@Query(value="update rides set available_seats=?2 where ride_id=?1", nativeQuery = true)
	void updateAvailableSeats(int rideId, String availableSeats);

	@Query(value="select * from rides where user_id=?1 and ride_date > ?2 and cancelled=0", nativeQuery = true)
	List<Rides> getMyRides(int userId, String currentDate);

	@Modifying(clearAutomatically = true)
	@Query(value="update rides set cancelled=1 where ride_id=?1", nativeQuery = true)
	void cancelRide(int rideId);

}
