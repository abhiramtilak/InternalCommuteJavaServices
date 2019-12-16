package com.commute.serviceImpl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commute.DAO.RideDAO;
import com.commute.bean.Ride;
import com.commute.db.model.Rides;
import com.commute.service.RideService;


@Component
public class RideServiceImpl implements RideService{
	
	@Autowired
	RideDAO rideDAO;

	public Rides postRide(Ride ride) {
		
		Rides ridesdb = new Rides();
		ridesdb.setUserId(ride.getUserId());
		ridesdb.setVehicle(ride.getVehicle());
		ridesdb.setAvailableSeats(ride.getAvailableSeats());
		ridesdb.setVehicleNumber(ride.getVehicleNumber());
		try {
		ridesdb.setRideDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ride.getRideDate()+" "+ride.getRideTime()));
		}catch(Exception e){
			System.out.println(e);
		}
		ridesdb.setRideTime(ride.getRideTime());
		Rides rides = rideDAO.postRide(ridesdb);
		return rides;
	}

}
