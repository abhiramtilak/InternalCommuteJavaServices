package com.commute.bean;

import org.springframework.http.HttpStatus;

public class ResponseBody {
	
	private HttpStatus responseCode;
	private String responseMessage;
	private String reponseBody;
	
	public HttpStatus getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(HttpStatus ok) {
		this.responseCode = ok;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getReponseBody() {
		return reponseBody;
	}
	public void setReponseBody(String reponseBody) {
		this.reponseBody = reponseBody;
	}
	
	

}
