package com.commute.bean;

import java.util.Date;

public class RideDetails {
	
	private int rideId;
	
	private String userId;
	
	private String riderName;
	
	private String riderMobileNumber;
	
	private String vehicle;
	
	private String availableSeats;
	
	private String vehicleNumber;
	
	private Date rideDateTime;
	
	private String fromAddress;
	
	private String toAddress;

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

	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	public String getRiderMobileNumber() {
		return riderMobileNumber;
	}

	public void setRiderMobileNumber(String riderMobileNumber) {
		this.riderMobileNumber = riderMobileNumber;
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

	public Date getRideDateTime() {
		return rideDateTime;
	}

	public void setRideDateTime(Date rideDateTime) {
		this.rideDateTime = rideDateTime;
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
	
}
