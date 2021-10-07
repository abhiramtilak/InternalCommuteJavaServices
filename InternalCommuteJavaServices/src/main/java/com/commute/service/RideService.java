package com.commute.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.commute.bean.ResponseBody;
import com.commute.bean.Ride;
import com.commute.bean.RideDetails;
import com.commute.bean.RideRequestDetails;
import com.commute.db.model.Rides;

@Component
public interface RideService {

	Rides postRide(Ride ride);

	List<RideDetails> getAllRides();

	ResponseBody requestRide(RideDetails rideDetails);

	List<RideRequestDetails> getRideRequests(int userId);

	List<Rides> getMyRides(int userId);

	void cancelRide(int rideId);

	void acceptRideRequest(int requestId);

	void rejectRideRequest(int requestId);

}
