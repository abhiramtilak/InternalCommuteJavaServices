package com.commute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.commute.bean.Ride;
import com.commute.db.model.Rides;
import com.commute.service.RideService;


@RestController
@RequestMapping("/rides")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RidesController {
	
	@Autowired
	RideService rideService;
	
	@RequestMapping(value = "/postRide", method = RequestMethod.POST)
	public ResponseEntity<Rides> postRide( @RequestBody Ride ride ) {
		
		Rides rides = rideService.postRide(ride);
		if( rides.getRideId() != 0 ) {
			return new ResponseEntity<Rides>(rides, HttpStatus.OK);
		}else {
			return new ResponseEntity<Rides>(rides, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
