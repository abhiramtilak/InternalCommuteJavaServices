package com.commute.DAOImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.commute.DAO.RideDAO;
import com.commute.db.model.Rides;
import com.commute.db.repository.RideRepository;
import com.commute.utils.DateUtils;

@Component
@Transactional
public class RideDAOImpl implements RideDAO{
	
	@Autowired
	RideRepository rideRepository;

	public Rides postRide(Rides rides) {
		Rides ridesdb = rideRepository.save(rides);
		return ridesdb;
	}

	public List<Rides> getAllRides() {
		
		return rideRepository.getAllRides(DateUtils.getCurrentDateTime());
		
	}

}
