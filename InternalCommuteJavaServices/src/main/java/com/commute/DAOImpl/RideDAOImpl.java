package com.commute.DAOImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.commute.DAO.RideDAO;
import com.commute.bean.RideDetails;
import com.commute.bean.RideRequestDetails;
import com.commute.db.model.RideRequests;
import com.commute.db.model.Rides;
import com.commute.db.repository.RideRepository;
import com.commute.db.repository.RideRequestRepository;
import com.commute.utils.DateUtils;

@Component
@Transactional
public class RideDAOImpl implements RideDAO{
	
	@Autowired
	RideRepository rideRepository;
	
	@Autowired
	RideRequestRepository rideRequestRepository;

	public Rides postRide(Rides rides) {
		
		return rideRepository.save(rides);
	}

	public List<Rides> getAllRides() {
		
		return rideRepository.getAllRides(DateUtils.getCurrentDateTime());
		
	}

	public Rides getRideDetails(RideDetails rideDetails) {
		
		return rideRepository.getRideDetails(rideDetails.getRideId());
		
	}

	public RideRequests postRequest(RideRequests rideDetails) {
		
		return rideRequestRepository.save(rideDetails);
		
	}

	public void saveorUpdateDetails(Rides details) {
		rideRepository.updateAvailableSeats(details.getRideId(), details.getAvailableSeats());
		
	}

	public List<Object[]> getRideRequests(int userId) {
		return rideRequestRepository.getAllRideRequests(userId, DateUtils.getCurrentDateTime());		
	}

	public List<Rides> getMyRides(int userId) {
		
		return rideRepository.getMyRides(userId, DateUtils.getCurrentDateTime());
		
	}

	public void cancelRide(int rideId) {
		rideRepository.cancelRide(rideId);
		
	}
}
