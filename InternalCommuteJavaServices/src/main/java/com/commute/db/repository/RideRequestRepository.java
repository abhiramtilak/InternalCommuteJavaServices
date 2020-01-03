package com.commute.db.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commute.bean.RideRequestDetails;
import com.commute.db.model.RideRequests;

@Repository
@Transactional
public interface RideRequestRepository extends JpaRepository<RideRequests, String>{

	@Query(value = "select rr.request_id,u.first_name,u.last_name,u.mobile_number,r.ride_date,r.from_address,r.to_address\r\n" + 
			"from rides r \r\n" + 
			"inner join ride_requests rr\r\n" + 
			"on r.ride_id = rr.ride_id \r\n" + 
			"inner join users u\r\n" + 
			"on u.user_id = rr.ride_taker_id\r\n" + 
			"where rr.rider_id=?1\r\n" + 
			"and r.ride_date> ?2 and r.cancelled=0", nativeQuery = true)
	List<Object[]> getAllRideRequests(int userId, String date);

	

}