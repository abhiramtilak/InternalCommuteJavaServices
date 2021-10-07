package com.commute.db.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commute.db.model.RideRequests;

@Repository
@Transactional
public interface RideRequestRepository extends JpaRepository<RideRequests, String>{

	@Query(value = "select rr.request_id,u.first_name,u.last_name,u.mobile_number,r.ride_date,r.from_address,r.to_address\r\n" + 
			"from rides r \r\n" + 
			"inner join ride_requests rr\r\n" + 
			"on r.ride_id = rr.ride_id and rr.status = 'REQUESTED' \r\n" + 
			"inner join users u\r\n" + 
			"on u.user_id = rr.ride_taker_id\r\n" + 
			"where rr.rider_id=?1\r\n" + 
			"and r.ride_date> ?2 and r.cancelled=0 ", nativeQuery = true)
	List<Object[]> getAllRideRequests(int userId, String date);

	@Modifying(clearAutomatically = true)
	@Query(value = "update ride_requests set status = ?2 where request_id=?1", nativeQuery = true)
	void acceptRideRequest(int requestId, String status);

	
	@Query(value = "select u.role, u.first_name, u.last_name, u.mobile_number, r.from_address, r.to_address, u.email, r.ride_date  from users u\r\n" + 
			"inner join ride_requests rr\r\n" + 
			"on u.user_id=rr.rider_id or u.user_id=rr.ride_taker_id\r\n" + 
			"inner join rides r\r\n" + 
			"on r.ride_id=rr.ride_id\r\n" + 
			"where rr.request_id=?1", nativeQuery = true)
	List<Object[]> getDetails(int requestId);

	@Query(value = "select count(*) from ride_requests where ride_id=?1 and (status= 'ACCEPTED'  or status = 'REQUESTED')", nativeQuery = true)
	String getAvailableSeats(int rideId);

	@Modifying(clearAutomatically = true)
	@Query(value = "update ride_requests set status = ?2 where request_id=?1", nativeQuery = true)
	void rejectRideRequest(int requestId, String status);

	@Query(value = "select u.email, r.from_address, r.to_address, r.ride_date, u.first_name, u.last_name from ride_requests rr \r\n" + 
			"inner join users u\r\n" + 
			"on u.user_id=rr.ride_taker_id\r\n" + 
			"inner join rides r\r\n" + 
			"on r.ride_id = rr.ride_id\r\n" + 
			"where rr.ride_id=?1 and (rr.status='REQUESTED' or rr.status='ACCEPTED')", nativeQuery = true)
	List<Object[]> getRideTakerDetails(int rideId);

	

}