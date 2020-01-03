package com.commute.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.commute.bean.RideDetails;
import com.commute.db.model.RideRequests;
import com.commute.db.model.Rides;

@Component
public interface RideDAO {

	Rides postRide(Rides rides);

	List<Rides> getAllRides();

	Rides getRideDetails(RideDetails rideDetails);

	RideRequests postRequest(RideRequests rideDetails);

	void saveorUpdateDetails(Rides details);

	List<Object[]> getRideRequests(int userId);

	List<Rides> getMyRides(int userId);

	void cancelRide(int rideId);

}
