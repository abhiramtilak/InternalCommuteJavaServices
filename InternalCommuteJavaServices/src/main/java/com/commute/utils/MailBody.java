package com.commute.utils;

import java.util.Date;

public class MailBody {
	
	public static String acceptedRiderBody(String riderName, String rideTakerName, String rideTakerMobile) {
		
		return "Hi "+riderName+",\n\n You Accepted the ride request from "+rideTakerName+". Contact number of ride taker is "+rideTakerMobile+". \n Please updatethe ride taker before hand in case of any ride cancellation. \n\n Thank you.";
	}
	
	public static String acceptedRideTakerBody(String riderName, String rideTakerName, String fromAddress, String toAddress, String riderMobile) {
		
		return "Hi "+rideTakerName+",\n\n Your ride request from "+fromAddress+" to "+toAddress+" got accepted. \n Rider name is "+riderName+". Contact number is "+riderMobile+". Please update rider before hand in case of ride request cancellation. \n\n Thank you.";
	}
	
	public static String rejectRideTakserBody(String rideTakerName, String fromAddress, String toAddress, Date rideTime) {
		return "Hi "+rideTakerName+",\n\n Your ride request from "+fromAddress+" to "+toAddress+" at "+rideTime+" got declined. Please find other active rides. \n \n  Thank you.";
	}
	
	public static String cancelledRide(String name, String fromAddress, String toAddress, Date rideDate) {
		return "Hi "+name+",\n\n You ride request from "+fromAddress+" to "+toAddress+" at "+rideDate+" got cancelled by rider. Please find other active rides.  \n \n  Thank you.";
	}

}
