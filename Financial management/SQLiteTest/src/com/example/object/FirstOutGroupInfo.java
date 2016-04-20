package com.example.object;

public class FirstOutGroupInfo {

	private String myFirstOutGroupNo=null;
	private String myFirstOutGroupName=null;
	
	public FirstOutGroupInfo(){
		
	}
	
	public FirstOutGroupInfo(String pFirstOutGroupNo,String pFirstOutGroupName){
		
		this.myFirstOutGroupName=pFirstOutGroupName;
		this.myFirstOutGroupNo=pFirstOutGroupNo;
	}

	public String getMyFirstOutGroupNo() {
		return myFirstOutGroupNo;
	}

	public void setMyFirstOutGroupNo(String myFirstOutGroupNo) {
		this.myFirstOutGroupNo = myFirstOutGroupNo;
	}

	public String getMyFirstOutGroupName() {
		return myFirstOutGroupName;
	}

	public void setMyFirstOutGroupName(String myFirstOutGroupName) {
		this.myFirstOutGroupName = myFirstOutGroupName;
	}
	
}
