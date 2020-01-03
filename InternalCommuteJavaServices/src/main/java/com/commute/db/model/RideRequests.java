package com.commute.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table( name="ride_requests" )
@Entity
public class RideRequests {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "requestId")
    private int requestId;
	
	@Column(name = "riderId")
	private String riderId;
	
	@Column(name = "rideTakerId")
	private String rideTakerId;
	
	@Column(name = "rideId")
	private String rideId;
	
	@Column(name = "status")
	private String status;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRiderId() {
		return riderId;
	}

	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}

	public String getRideTakerId() {
		return rideTakerId;
	}

	public void setRideTakerId(String rideTakerId) {
		this.rideTakerId = rideTakerId;
	}

	public String getRideId() {
		return rideId;
	}

	public void setRideId(String rideId) {
		this.rideId = rideId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
