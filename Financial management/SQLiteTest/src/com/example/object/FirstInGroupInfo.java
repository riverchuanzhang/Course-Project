package com.example.object;

public class FirstInGroupInfo {

	private String myFirstInGroupNo=null;
	private String myFirstInGroupName=null;
	
	public FirstInGroupInfo(){
		
	}
	
	public FirstInGroupInfo(String pFirstInGroupNo,String pFirstInGroupName){
		this.myFirstInGroupName=myFirstInGroupName;
		this.myFirstInGroupNo=myFirstInGroupNo;
	}

	public String getMyFirstInGroupNo() {
		return myFirstInGroupNo;
	}

	public void setMyFirstInGroupNo(String myFirstInGroupNo) {
		this.myFirstInGroupNo = myFirstInGroupNo;
	}

	public String getMyFirstInGroupName() {
		return myFirstInGroupName;
	}

	public void setMyFirstInGroupName(String myFirstInGroupName) {
		this.myFirstInGroupName = myFirstInGroupName;
	}
	
}
