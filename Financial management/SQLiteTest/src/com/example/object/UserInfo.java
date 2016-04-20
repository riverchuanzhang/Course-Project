package com.example.object;

public class UserInfo{
	
	private String myUserEmail=null;
	private String myUserPassword=null;
	private String myUserName=null;
	
	public UserInfo(){
		
	}
	
	public UserInfo(String pUserEmail,String pUserPassword,String pUserName){
		
		this.myUserEmail=pUserEmail;
		this.myUserName=pUserName;
		this.myUserPassword=pUserPassword;
	}

	public String getMyUserEmail() {
		return myUserEmail;
	}

	public void setMyUserEmail(String myUserEmail) {
		this.myUserEmail = myUserEmail;
	}

	public String getMyUserPassword() {
		return myUserPassword;
	}

	public void setMyUserPassword(String myUserPassword) {
		this.myUserPassword = myUserPassword;
	}

	public String getMyUserName() {
		return myUserName;
	}

	public void setMyUserName(String myUserName) {
		this.myUserName = myUserName;
	}
	
}
