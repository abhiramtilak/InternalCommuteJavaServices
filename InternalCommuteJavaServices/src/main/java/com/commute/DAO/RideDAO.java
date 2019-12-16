package com.commute.DAO;

import org.springframework.stereotype.Component;

import com.commute.db.model.Rides;

@Component
public interface RideDAO {

	Rides postRide(Rides rides);

}
