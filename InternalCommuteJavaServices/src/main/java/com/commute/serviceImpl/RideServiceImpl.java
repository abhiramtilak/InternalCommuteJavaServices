package com.commute.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.commute.DAO.LoginDAO;
import com.commute.DAO.RideDAO;
import com.commute.bean.ResponseBody;
import com.commute.bean.Ride;
import com.commute.bean.RideDetails;
import com.commute.bean.RideRequestDetails;
import com.commute.db.model.RideRequests;
import com.commute.db.model.Rides;
import com.commute.db.model.Users;
import com.commute.service.RideService;
import com.commute.utils.MailBody;
import com.commute.utils.RideStatus;
import com.commute.utils.SendMail;


@Component
public class RideServiceImpl implements RideService{
	
	@Autowired
	RideDAO rideDAO;
	
	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	SendMail sendMail;

	public Rides postRide(Ride ride) {
		
		Rides ridesdb = new Rides();
		ridesdb.setUserId(ride.getUserId());
		ridesdb.setVehicle(ride.getVehicle());
		ridesdb.setAvailableSeats(ride.getAvailableSeats());
		ridesdb.setVehicleNumber(ride.getVehicleNumber());
		ridesdb.setFromAddress(ride.getFromAddress());
		ridesdb.setToAddress(ride.getToAddress());
		ridesdb.setCancelled(false);
		try {
		ridesdb.setRideDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ride.getRideDate()+" "+ride.getRideTime()));
		}catch(Exception e){
			System.out.println(e);
		}
		ridesdb.setRideTime(ride.getRideTime());
		Rides rides = rideDAO.postRide(ridesdb);
		return rides;
	}

	public List<RideDetails> getAllRides() {
		List<Rides> allRides = rideDAO.getAllRides();
		List<RideDetails> rideDetailsList = new ArrayList<RideDetails>();
		for( Rides rides : allRides ) {
			RideDetails rideDetails = new RideDetails();
			rideDetails.setAvailableSeats( (Integer.parseInt(rides.getAvailableSeats()) - Integer.parseInt(rideDAO.getAvailableSeats(rides.getRideId())))+"" );
			rideDetails.setRideDateTime(rides.getRideDate());
			rideDetails.setRideId(rides.getRideId());
			Users user = loginDAO.validatePassword(rides.getUserId());
			if( user != null ) {
				rideDetails.setRiderName(user.getFirstName()+" "+user.getLastName());
				rideDetails.setRiderMobileNumber(user.getMobileNumber());
			}
			else {
				rideDetails.setRiderName("Not Available");
				rideDetails.setRiderName("Not Available");
			}
			rideDetails.setUserId(rides.getUserId());
			rideDetails.setVehicle(rides.getVehicle());
			rideDetails.setVehicleNumber(rides.getVehicleNumber());
			rideDetails.setFromAddress(rides.getFromAddress());
			rideDetails.setToAddress(rides.getToAddress());
			
			rideDetailsList.add(rideDetails);
		}
		return rideDetailsList;
		
	}

	public ResponseBody requestRide(RideDetails rideDetails) {
		
		Rides details = rideDAO.getRideDetails(rideDetails);
		ResponseBody response = new ResponseBody();
		if( Integer.parseInt(details.getAvailableSeats()) > 0 ) {
			RideRequests rideRequest = new RideRequests();
			rideRequest.setRideId(rideDetails.getRideId()+"");
			rideRequest.setRiderId(rideDetails.getUserId());
			rideRequest.setRideTakerId(rideDetails.getRideTakerId());
			rideRequest.setStatus(RideStatus.REQUESTED.toString());
			RideRequests repsonseRequest = rideDAO.postRequest(rideRequest);
			if( repsonseRequest.getRequestId()+"" != "" ) {
				
				response.setResponseMessage("Ride request is successful!! You will recieve mail with details once rider accepts ride request.");
				response.setResponseCode(HttpStatus.OK);
			}else {
				response.setResponseMessage("Some thing went wrong. Please try again later!!");
				response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			response.setResponseMessage("Seats are not available. Already booked!! Please refresh page to get available rides.");
			response.setResponseCode(HttpStatus.CONFLICT);
		}
		return response;
	}

	public List<RideRequestDetails> getRideRequests(int userId) {
		List<Object[]> rideRequests = rideDAO.getRideRequests(userId);
		List<RideRequestDetails> requestDetails = new ArrayList<RideRequestDetails>();
		if( rideRequests.size() > 0 ) {
			for( Object[] o: rideRequests ) {
				RideRequestDetails details = new RideRequestDetails();
				details.setRequestId(Integer.parseInt(o[0].toString()));
				details.setFirstName(o[1].toString());
				details.setLastName(o[2].toString());
				details.setMobileNumber(o[3].toString());
				details.setRideDate( (Date) o[4]);
				details.setFromAddress(o[5].toString());
				details.setToAddress(o[6].toString());
				requestDetails.add(details);
				
			}
		}
		return requestDetails;
		
	}

	public List<Rides> getMyRides(int userId) {
		List<Rides> allRides = rideDAO.getMyRides(userId); 
		return allRides;
	}

	public void cancelRide(int rideId) {
		rideDAO.cancelRide(rideId);
		List<Object[]> rideTakerDetails = rideDAO.getRideTakerDetails(rideId);
		String email="";
		String fromAddress="";
		String toAddress="";
		String name="";
		Date rideDate=new Date();
		if( rideTakerDetails.size()>0 ) {
			for( Object[] o : rideTakerDetails ) {
				email = o[0].toString();
				fromAddress= o[1].toString();
				toAddress=o[2].toString();
				rideDate=(Date)o[3];
				name=o[4]+" "+o[5];
			}
		}
		sendMail.sendMail(email, "Ride got cancelled.", MailBody.cancelledRide(name, fromAddress, toAddress, rideDate));
		
	}

	public void acceptRideRequest(int requestId) {
		
		rideDAO.acceptRideRequest(requestId);
		
		List<Object[]> details = rideDAO.getDetails(requestId);
		String riderName="";
		String rideTakerName="";
		String riderMobile="";
		String rideTakerMobile="";
		String fromAddress="";
		String toAddress="";
		String riderMailId = "";
		String rideTakerMailId="";
		Date rideDate = new Date();
		
		if( details.size() > 0 ) {
			for( Object[] o: details ) {
				
				if( o[0].toString().equalsIgnoreCase("RideTaker") ) {
					rideTakerName = o[1].toString()+" "+o[2].toString();
					rideTakerMobile = o[3].toString();
					fromAddress = o[4].toString();
					toAddress = o[5].toString();
					rideTakerMailId = o[6].toString();
					rideDate = (Date)o[7];
				}else if( o[0].toString().equalsIgnoreCase("Rider") ){
					riderName = o[1].toString()+" "+o[2].toString();
					riderMobile = o[3].toString();
					riderMailId = o[6].toString();
				}
				
			}
		}
		sendMail.sendMail(riderMailId, "Accepted ride request dated on "+rideDate, MailBody.acceptedRiderBody(riderName, rideTakerName, rideTakerMobile));
		sendMail.sendMail(rideTakerMailId, "Accepted ride request dated on "+rideDate, MailBody.acceptedRideTakerBody(riderName, rideTakerName, fromAddress, toAddress, riderMobile));
		
	}

	public void rejectRideRequest(int requestId) {
		
		rideDAO.rejectRideRequest(requestId);
		
		List<Object[]> details = rideDAO.getDetails(requestId);
		String rideTakerName="";
		String fromAddress="";
		String toAddress="";
		String rideTakerMailId = "";
		Date rideDate = new Date();
		
		if( details.size() > 0 ) {
			for( Object[] o: details ) {
				
				if( o[0].toString().equalsIgnoreCase("RideTaker") ) {
					rideTakerName = o[1].toString()+" "+o[2].toString();
					fromAddress = o[4].toString();
					rideTakerMailId = o[6].toString();
					toAddress = o[5].toString();
					rideDate = (Date)o[7];
				}
				
			}
		}
		sendMail.sendMail(rideTakerMailId, "Rejected ride request dated on "+rideDate, MailBody.rejectRideTakserBody(rideTakerName, fromAddress, toAddress, rideDate));
		
	}

}
