package com.commute.service;

import org.springframework.stereotype.Component;

import com.commute.bean.Ride;
import com.commute.db.model.Rides;

@Component
public interface RideService {

	Rides postRide(Ride ride);

}
