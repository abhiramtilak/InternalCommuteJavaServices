package com.commute.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table( name="users" )
@Entity
public class Users {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
    private int userId;
	
	@Column(name = "firstName")
    private String firstName;
	
	@Column(name = "lastName")
    private String lastName;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "mobileNumber")
    private String mobileNumber;
	
	@Column(name = "role")
    private String role;
	
	@Column(name = "officeAddress")
    private String officeAddress;
	
	@Column(name = "homeAddress")
    private String homeAddress;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "vehicle")
    private String vehicle;
	
	@Column(name = "availableSeats")
    private String availableSeats;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
}
