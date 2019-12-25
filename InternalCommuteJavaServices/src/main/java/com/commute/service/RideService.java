package com.commute.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.commute.bean.Ride;
import com.commute.bean.RideDetails;
import com.commute.db.model.Rides;

@Component
public interface RideService {

	Rides postRide(Ride ride);

	List<RideDetails> getAllRides();

}
