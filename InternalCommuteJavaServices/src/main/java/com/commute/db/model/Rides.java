package com.commute.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table( name="rides" )
@Entity
public class Rides {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rideId")
    private int rideId;
	
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "vehicle")
	private String vehicle;
	
	@Column(name = "availableSeats")
	private String availableSeats;
	
	@Column(name = "vehicleNumber")
	private String vehicleNumber;
	
	@Column(name = "rideDate")
	private Date rideDate;
	
	@Column(name = "rideTime")
	private String rideTime;
	
	@Column(name = "fromAddress")
	private String fromAddress;
	
	@Column(name = "toAddress")
	private String toAddress;
	
	@Column(name = "cancelled")
	private Boolean cancelled;

	public int getRideId() {
		return rideId;
	}

	public void setRideId(int rideId) {
		this.rideId = rideId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(String availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Date getRideDate() {
		return rideDate;
	}

	public void setRideDate(Date rideDate) {
		this.rideDate = rideDate;
	}

	public String getRideTime() {
		return rideTime;
	}

	public void setRideTime(String rideTime) {
		this.rideTime = rideTime;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}
	
}
