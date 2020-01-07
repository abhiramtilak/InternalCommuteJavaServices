package com.commute.DAOImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.commute.DAO.RideDAO;
import com.commute.bean.RideDetails;
import com.commute.db.model.RideRequests;
import com.commute.db.model.Rides;
import com.commute.db.repository.RideRepository;
import com.commute.db.repository.RideRequestRepository;
import com.commute.utils.DateUtils;
import com.commute.utils.RideStatus;

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

	public List<Object[]> getRideRequests(int userId) {
		return rideRequestRepository.getAllRideRequests(userId, DateUtils.getCurrentDateTime());		
	}

	public List<Rides> getMyRides(int userId) {
		
		return rideRepository.getMyRides(userId, DateUtils.getCurrentDateTime());
		
	}

	public void cancelRide(int rideId) {
		rideRepository.cancelRide(rideId);
		
	}

	public void acceptRideRequest(int requestId) {
		
		rideRequestRepository.acceptRideRequest(requestId, RideStatus.ACCEPTED.toString());
		
	}

	public List<Object[]> getDetails(int requestId) {
		
		return rideRequestRepository.getDetails(requestId);
	}

	public String getAvailableSeats(int rideId) {
		
		return rideRequestRepository.getAvailableSeats(rideId);
	}

	public void rejectRideRequest(int requestId) {
		
		rideRequestRepository.rejectRideRequest(requestId, RideStatus.REJECTED.toString());
		
	}

	public List<Object[]> getRideTakerDetails(int rideId) {
		
		return rideRequestRepository.getRideTakerDetails(rideId);
		
	}
}
