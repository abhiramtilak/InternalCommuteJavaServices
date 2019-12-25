package com.commute.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commute.DAO.LoginDAO;
import com.commute.DAO.RideDAO;
import com.commute.bean.Ride;
import com.commute.bean.RideDetails;
import com.commute.db.model.Rides;
import com.commute.db.model.Users;
import com.commute.service.RideService;


@Component
public class RideServiceImpl implements RideService{
	
	@Autowired
	RideDAO rideDAO;
	
	@Autowired
	LoginDAO loginDAO;

	public Rides postRide(Ride ride) {
		
		Rides ridesdb = new Rides();
		ridesdb.setUserId(ride.getUserId());
		ridesdb.setVehicle(ride.getVehicle());
		ridesdb.setAvailableSeats(ride.getAvailableSeats());
		ridesdb.setVehicleNumber(ride.getVehicleNumber());
		ridesdb.setFromAddress(ride.getFromAddress());
		ridesdb.setToAddress(ride.getToAddress());
		try {
		ridesdb.setRideDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ride.getRideDate()+" "+ride.getRideTime()));
		}catch(Exception e){
			System.out.println(e);
		}
		ridesdb.setRideTime(ride.getRideTime());
		Rides rides = rideDAO.postRide(ridesdb);
		return rides;
	}

	public List<RideDetails> getAllRides() {
		List<Rides> allRides = rideDAO.getAllRides();
		List<RideDetails> rideDetailsList = new ArrayList<RideDetails>();
		for( Rides rides : allRides ) {
			RideDetails rideDetails = new RideDetails();
			rideDetails.setAvailableSeats(rides.getAvailableSeats());
			rideDetails.setRideDateTime(rides.getRideDate());
			rideDetails.setRideId(rides.getRideId());
			Users user = loginDAO.validatePassword(rides.getUserId());
			if( user != null ) {
				rideDetails.setRiderName(user.getFirstName()+" "+user.getLastName());
				rideDetails.setRiderMobileNumber(user.getMobileNumber());
			}
			else {
				rideDetails.setRiderName("Not Available");
				rideDetails.setRiderName("Not Available");
			}
			rideDetails.setUserId(rides.getUserId());
			rideDetails.setVehicle(rides.getVehicle());
			rideDetails.setVehicleNumber(rides.getVehicleNumber());
			rideDetails.setFromAddress(rides.getFromAddress());
			rideDetails.setToAddress(rides.getToAddress());
			
			rideDetailsList.add(rideDetails);
		}
		return rideDetailsList;
		
	}

}
