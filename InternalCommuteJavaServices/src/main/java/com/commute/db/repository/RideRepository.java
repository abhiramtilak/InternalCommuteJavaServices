package com.commute.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commute.db.model.Rides;

@Repository
@Transactional
public interface RideRepository extends JpaRepository<Rides, String>{

}
