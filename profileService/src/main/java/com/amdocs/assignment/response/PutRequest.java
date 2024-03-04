package com.amdocs.assignment.response;

public class PutRequest {
	private String address;
	private String PhoneNo;
	public String getAddress() {
		return address;
	}
	public String getPhoneNo() {
		return PhoneNo;
	
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public PutRequest(String address, String phoneNo) {
		super();
		this.address = address;
		PhoneNo = phoneNo;
	}
}
