package com.commute.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commute.bean.ResponseBody;
import com.commute.bean.Ride;
import com.commute.bean.RideDetails;
import com.commute.bean.RideRequestDetails;
import com.commute.db.model.Rides;
import com.commute.service.RideService;


@RestController
@RequestMapping("/rides")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RidesController {
	
	public static final Logger logger = LoggerFactory.getLogger(RidesController.class);
	
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
	
	@RequestMapping(value = "/getRides", method = RequestMethod.GET)
	public ResponseEntity<List<RideDetails>> getRides( ) {
		
		return new ResponseEntity<List<RideDetails>>(rideService.getAllRides(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/requestRide", method = RequestMethod.POST)
	public ResponseEntity<ResponseBody> requestRide( @RequestBody RideDetails rideDetails ) {
		
		return new ResponseEntity<ResponseBody>(rideService.requestRide(rideDetails), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getRideRequests", method = RequestMethod.GET)
	public ResponseEntity<List<RideRequestDetails>> getRideRequests( @RequestParam int userId ) {
		
		logger.info("Getting ride requests for userId ::"+userId);
		
		return new ResponseEntity<List<RideRequestDetails>>(rideService.getRideRequests(userId), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/myRides", method = RequestMethod.GET)
	public ResponseEntity<List<Rides>> myRides( @RequestParam int userId ) {
		
		logger.info("Getting all ride for userId ::"+userId);
		return new ResponseEntity<List<Rides>>(rideService.getMyRides(userId), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/cancelRide", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseBody> cancelRide( @RequestParam int rideId ) {
		
		logger.info("cancelling ride with ride id ::"+rideId);
		rideService.cancelRide(rideId);
		ResponseBody response = new ResponseBody();
		response.setResponseCode(HttpStatus.OK);
		response.setResponseMessage("Ride cancelled successfully");
		
		return new ResponseEntity<ResponseBody>(response, HttpStatus.OK);
		
	}

}
