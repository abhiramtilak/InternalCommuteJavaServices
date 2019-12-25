package com.commute.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.commute.db.model.Rides;

@Component
public interface RideDAO {

	Rides postRide(Rides rides);

	List<Rides> getAllRides();

}
