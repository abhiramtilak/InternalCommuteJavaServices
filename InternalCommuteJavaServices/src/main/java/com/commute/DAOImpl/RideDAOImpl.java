package com.commute.DAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.commute.DAO.RideDAO;
import com.commute.db.model.Rides;
import com.commute.db.repository.RideRepository;

@Component
@Transactional
public class RideDAOImpl implements RideDAO{
	
	@Autowired
	RideRepository rideRepository;

	public Rides postRide(Rides rides) {
		Rides ridesdb = rideRepository.save(rides);
		return ridesdb;
	}

}
