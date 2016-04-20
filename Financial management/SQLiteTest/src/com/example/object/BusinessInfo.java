package com.example.object;

public class BusinessInfo {

	private String myBusinessNo=null;
	private String myBusinessName=null;
	
	public BusinessInfo(){
		
	}
	
	public BusinessInfo(String pBusinessNo,String pBusinessName){
		this.myBusinessNo=pBusinessNo;
		this.myBusinessName=pBusinessName;
	}

	public String getMyBusinessNo() {
		return myBusinessNo;
	}

	public void setMyBusinessNo(String myBusinessNo) {
		this.myBusinessNo = myBusinessNo;
	}

	public String getMyBusinessName() {
		return myBusinessName;
	}

	public void setMyBusinessName(String myBusinessName) {
		this.myBusinessName = myBusinessName;
	}
	
}
