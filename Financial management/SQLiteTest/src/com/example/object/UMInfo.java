package com.example.object;

public class UMInfo {

	private String myManagerNo=null;
	private String myUserEmail=null;
	
	public UMInfo(){
		
	}
	
	public UMInfo(String pManagerNo,String pUserEmail){
		
		this.myManagerNo=pManagerNo;
		this.myUserEmail=pUserEmail;
	}

	public String getMyManagerNo() {
		return myManagerNo;
	}

	public void setMyManagerNo(String myManagerNo) {
		this.myManagerNo = myManagerNo;
	}

	public String getMyUserEmail() {
		return myUserEmail;
	}

	public void setMyUserEmail(String myUserEmail) {
		this.myUserEmail = myUserEmail;
	}
	
}
